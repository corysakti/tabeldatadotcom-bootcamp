package com.maryanto.dimas.example.bootcampspring.controller;

import java.util.List;

import com.maryanto.dimas.example.bootcampspring.entity.Product;
import com.maryanto.dimas.example.bootcampspring.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
        try {
            Product pro = repo.findById(id);
            return ResponseEntity.ok(pro);
        } catch (EmptyResultDataAccessException ertdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/list")
    public List<Product> list() {
        return this.repo.list();
    }
}
