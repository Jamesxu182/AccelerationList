package com.james.controller.rest;

import com.james.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by james on 6/3/17.
 */
@RestController
public class TodoController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String secure() {
        return "secure";
    }
}