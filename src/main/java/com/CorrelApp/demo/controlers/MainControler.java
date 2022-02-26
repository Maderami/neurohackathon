package com.CorrelApp.demo.controlers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class MainControler {
    @GetMapping("/home")
    public String home() {

        return "index";
    }
    @GetMapping("/services")
    public String services() {

        return "services";
    }

}



