package com.maryanto.dimas.example.bootcampspring.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
