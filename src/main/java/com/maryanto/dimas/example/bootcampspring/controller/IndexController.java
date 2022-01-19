package com.maryanto.dimas.example.bootcampspring.controller;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/web")
public class IndexController {

    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("data", new Department());
        return "index";
    }

    @PostMapping("/test")
    public String processRequest(
            @RequestParam(name = "nama") String nama,
            @RequestParam("description") String description) {
        System.out.println("nama: " + nama + ", description: " + description);
        return "redirect:/web/index";
    }

    @PostMapping("/test2")
    public String processRequest2(
            @ModelAttribute Department dept) {
        System.out.println("nama: " + dept.getNama() + ", description: " + dept.getDescription());
        return "redirect:/web/index";
    }
}
