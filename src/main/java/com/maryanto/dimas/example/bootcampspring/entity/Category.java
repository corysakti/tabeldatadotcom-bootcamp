package com.maryanto.dimas.example.bootcampspring.entity;

public class Category {
    
    public Integer id;
    private Integer department_id;
    private String nama;    
    private String description;
    public Department department;

    
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    

    public Integer getDepartment_id() {
        return department_id;
    }
    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public Category() {
    
    }
    
    
    public Category(Integer id, Integer department_id, String nama, String description) {
        this.id = id;
        this.department_id = department_id;
        this.nama = nama;
        this.description = description;
    }

    

    public Category(Integer id, String nama, String description, Department department) {
        this.id = id;
        
        this.nama = nama;
        this.description = description;
        this.department = department;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}
