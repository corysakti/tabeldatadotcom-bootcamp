package com.maryanto.dimas.example.bootcampspring.controller;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
    public ResponseEntity<?> save( @Valid @RequestBody Department dep, BindingResult result) {
        Map<String, Object> hasil = new HashMap<>();
        if(result.hasErrors()){
            hasil.put("id", -1);
            hasil.put("status", result.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
        }else {
            dep = this.repo.insert(dep);
            if (dep.getId() == null) {
                return ResponseEntity.internalServerError().body("Gak tau errornya apa");
            } else {
                
                Map<String, Object> hasil2 = new HashMap<>();
                hasil2.put("id", dep.id);
                hasil2.put("status", "Simpan Berhasil");
                return ResponseEntity.ok(dep);
            }
        }
        
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Department dept) {
        dept.setId(id);
        this.repo.updateById(dept);
        if (dept.getId() == null) {
            return ResponseEntity.internalServerError().body("Gak tau errornya apa, mungkin =");
        } else {
            
            return ResponseEntity.ok(dept);
        }
    }
}
