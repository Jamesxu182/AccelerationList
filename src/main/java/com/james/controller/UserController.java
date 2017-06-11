package com.james.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by james on 6/11/17.
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
    @RequestMapping("/username")
    @ResponseBody
    public String user(Authentication authentication)
    {
        return authentication.getName();
    }
}
