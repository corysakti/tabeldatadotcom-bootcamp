package com.maryanto.dimas.example.bootcampspring.entity;

public class Department {

    public Department() {
    }

    public Department(Integer id, String nama, String description) {
        this.id = id;
        this.nama = nama;
        this.description = description;
    }

    public Integer id;
    private String nama;
    private String description;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
