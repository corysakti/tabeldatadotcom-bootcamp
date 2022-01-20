package com.maryanto.dimas.example.bootcampspring.controller;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web")
public class IndexApiController {

    @GetMapping(value = "/index",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String example() {
        return "{\n" +
                "  \"message\"" +
                ": \"testing\"\n" +
                "}";
    }

    @GetMapping(value = "/index2",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String exampleWithParam(@RequestParam String nama, @RequestParam Integer tahun) {

        return "{\n" +
                "  \"" + nama + "\"" +
                ": \"" + tahun + "\"\n" +
                "}";
    }
}
