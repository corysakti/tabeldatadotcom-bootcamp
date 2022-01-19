package com.maryanto.dimas.example.bootcampspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/web")
public class IndexController {

    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("tanggal", new Date());
        return "index";
    }
}
