package com.anirudh.curd.controller;

import com.anirudh.curd.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController { //Controller only deals with the request and response

    @Autowired
    private HomeService homeService;

    @GetMapping("/home")
    public String home(){
        return homeService.home();
    }
}
