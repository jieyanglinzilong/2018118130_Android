package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class indexCotroller {
    @RequestMapping(value = "/adduser")
    public String index1(){

        return "adduser";
    }
}
