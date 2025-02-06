package com.anirudh.curd.services;

import org.springframework.stereotype.Service;

@Service
public class HomeService { // Service deals with the business logic (functionality)

    public String home(){
        return "Hello From Server";
    }
}
