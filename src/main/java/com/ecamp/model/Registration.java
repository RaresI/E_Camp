package com.ecamp.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** When the registration was made */
    private Instant registrationDate = Instant.now();

    /** Total cost (camp + selected activities/trips) */
    private BigDecimal totalCost;

    /** e.g. "PENDING", "CONFIRMED", "CANCELLED" */
    private String status;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;

    @ManyToOne
    @JoinColumn(name = "camp_id", nullable = false)
    private Camp camp;

    @ManyToMany
    @JoinTable(
            name = "registration_trip",
            joinColumns = @JoinColumn(name = "registration_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id")
    )
    private List<Trip> trips;

    public List<Trip> getTrips() { return trips; }
    public void setTrips(List<Trip> trips) { this.trips = trips; }


    // ─── Getters & Setters ──────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Instant getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Instant registrationDate) {
        this.registrationDate = registrationDate;
    }

    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        this.status = status;
    }

    public Child getChild() { return child; }
    public void setChild(Child child) {
        this.child = child;
    }

    public Camp getCamp() { return camp; }
    public void setCamp(Camp camp) {
        this.camp = camp;
    }

}
