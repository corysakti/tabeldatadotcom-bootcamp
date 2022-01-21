package com.maryanto.dimas.example.bootcampspring.controller;

import java.util.List;

import com.maryanto.dimas.example.bootcampspring.entity.Category;
import com.maryanto.dimas.example.bootcampspring.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired
    public CategoryRepository catRepo;

    @GetMapping("/{depId}")
    public ResponseEntity<Category> findById(@PathVariable("depId") Integer id) {
        try {
            Category cat = catRepo.findById(id);
            return ResponseEntity.ok(cat);
        } catch (EmptyResultDataAccessException ertdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public List<Category> list() {
        return this.catRepo.listJoin();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category cat) {
        cat = this.catRepo.insert(cat);
        if (cat.getId() == null) {
            return ResponseEntity.internalServerError().body("Gak tau errornya apa");
        } else {
            return ResponseEntity.ok(cat);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Category cat) {
        cat.setId(id);
        Category cats = this.catRepo.updateById(cat);
        if (cats.getId() == null) {
            return ResponseEntity.internalServerError().body("Gak tau errornya apa, mungkin =");
        } else {
            
            return ResponseEntity.ok(cat);
        }
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        this.catRepo.deleteById(id);;
    }


}
