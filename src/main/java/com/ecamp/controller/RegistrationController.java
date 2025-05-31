package com.ecamp.controller;

import com.ecamp.model.Registration;
import com.ecamp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired private RegistrationRepository repo;
    @Autowired private ChildRepository childRepo;
    @Autowired private CampRepository campRepo;
    @Autowired
    private TripRepository tripRepo;


    @GetMapping
    public List<Registration> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Registration getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public Registration create(@RequestBody Map<String, Object> b) {
        var r = new Registration();

        var child = childRepo.findById(Long.valueOf(b.get("childId").toString())).orElseThrow();
        var camp  = campRepo.findById(Long.valueOf(b.get("campId").toString())).orElseThrow();
        r.setChild(child);
        r.setCamp(camp);
        r.setRegistrationDate(java.time.Instant.now());
        r.setTotalCost(new java.math.BigDecimal("0"));
        r.setStatus(b.getOrDefault("status", "PENDING").toString());

        // Trips
        if (b.containsKey("tripIds")) {
            @SuppressWarnings("unchecked")
            List<Integer> rawIds = (List<Integer>) b.get("tripIds");
            List<Long> ids = rawIds.stream().map(Long::valueOf).toList();
            var trips = tripRepo.findAllById(ids);
            r.setTrips(trips);
        }

        return repo.save(r);
    }

    @GetMapping("/byChild/{childId}")
    public List<Registration> getByChild(@PathVariable Long childId) {
        return repo.findAll().stream()
                .filter(r -> r.getChild().getId().equals(childId))
                .toList();
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
