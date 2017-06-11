package com.james.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by james on 6/1/17.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index()
    {
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello()
    {
        return "Hello";
    }

    @RequestMapping(value = "/secure", method = RequestMethod.GET)
    @ResponseBody
    public String secure()
    {
        return "Hello, Secure.";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login()
//    {
//        return "login";
//    }
}
