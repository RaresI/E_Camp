package com.ecamp.dto;

public class ChildDTO {
    public Long id;
    public String name;
    public String school;
    public Long parentId;    // ← new field

    public ChildDTO(Long id, String name, String school, Long parentId) {
        this.id = id;
        this.name = name;
        this.school = school;
        this.parentId = parentId;
    }
}
