package com.maryanto.dimas.example.bootcampspring.controller;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentApiController {

    @Autowired
    private DepartmentRepository repo;

    @GetMapping("/findById/{depId}")
    public ResponseEntity<Department> findById(@PathVariable("depId") Integer id) {
        try {
            Department dep = repo.findById(id);
            return ResponseEntity.ok(dep);
        } catch (EmptyResultDataAccessException ertdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/list")
    public List<Department> list() {
        return this.repo.list();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Department dep) {
        dep = this.repo.insert(dep);
        if (dep.getId() == null) {
            return ResponseEntity.internalServerError().body("Gak tau errornya apa");
        } else {
            return ResponseEntity.ok(dep);
        }
    }
}
