package com.svirski.spring.web.controllers;

/**
 * Created by Vasili_Svirski on 6/5/2017.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        System.out.println("1");

        ModelAndView model = new ModelAndView("login");
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        System.out.println("2");

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }

        System.out.println("3");

        return model;
    }
}
