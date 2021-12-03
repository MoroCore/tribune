package org.moro.tribune.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
*
* Create by codedrinker on 2021.10.13
*
* */
@Controller
public class AuthorizeController {

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state ){



        return "index";
    }
}
