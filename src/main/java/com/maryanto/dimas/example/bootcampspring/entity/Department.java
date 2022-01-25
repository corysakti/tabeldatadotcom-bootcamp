package com.maryanto.dimas.example.bootcampspring.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Department {

    public Department() {
    }

    public Department(Integer id, String nama, String description) {
        this.id = id;
        this.nama = nama;
        this.description = description;
    }

    public Integer id;

    @NotEmpty
    @Length(min =  6)
    private String nama;

    @NotEmpty
    @Length(min = 5)
    private String description;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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
