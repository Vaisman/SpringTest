package com.svirski.spring.web.controllers;

import java.util.ArrayList;
import java.util.List;

import com.svirski.spring.core.models.Auditorium;
import com.svirski.spring.core.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vasili_Svirski on 5/29/2017.
 */
@Controller
@RequestMapping("/auditorium")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    private String usersPageName = "auditoriumPage";
    private String objectsListName = "objectsList";

    private ModelAndView GetModelView(List<?> list) {
        ModelAndView modelAndView = new ModelAndView(usersPageName);
        modelAndView.addObject(objectsListName, list);
        return modelAndView;
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public
    @ResponseBody
    ModelAndView getAuditoriums() {
        List<Auditorium> auditorium = auditoriumService.getAuditoriums();
        return GetModelView(auditorium);
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/number/by/name/{name}")
    public
    @ResponseBody
    ModelAndView getByName(@PathVariable("name") String name) {
        Integer seats = auditoriumService.getSeatsNumber(name);
        return GetModelView(new ArrayList<>(seats));
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/numbers/by/name/{name}")
    public
    @ResponseBody
    ModelAndView getSeatsNumber(@PathVariable("name") String name) {
        Integer seats = auditoriumService.getSeatsNumber(name);
        return GetModelView(new ArrayList<>(seats));
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/vip/{name}")
    public
    @ResponseBody
    ModelAndView getVipSeats(@PathVariable("name") String name) {
        List<Integer> auditorium = auditoriumService.getVipSeats(name);
        return GetModelView(auditorium);
    }
}
