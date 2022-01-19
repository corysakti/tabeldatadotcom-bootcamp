package com.maryanto.dimas.example.bootcampspring.controller;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class IndexController {

    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("data", new Department());
        return "index";
    }
}
