package com.james.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by james on 6/1/17.
 */
//@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index()
    {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login()
    {
        return new ModelAndView("login");
    }
}
