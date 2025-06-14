// com/ecamp/model/Parent.java
package com.ecamp.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Parent extends User{
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String name;
//    private String email;
//    private String phone;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Child> children;

//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//    public String getPhone() { return phone; }
//    public void setPhone(String phone) { this.phone = phone; }
    public List<Child> getChildren() { return children; }
    public void setChildren(List<Child> children) { this.children = children; }
}
