package com.svirski.spring.web.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.svirski.spring.core.models.Auditorium;
import com.svirski.spring.core.models.Event;
import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.DiscountService;
import com.svirski.spring.core.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vasili_Svirski on 5/29/2017.
 */
@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    private String eventPageName = "eventPage";
    private String objectsListName = "objectsList";

    /**
     *
     * @param list
     * @return
     */
    private ModelAndView GetModelView(List<?> list) {
        ModelAndView modelAndView = new ModelAndView(eventPageName);
        modelAndView.addObject(objectsListName, list);
        return modelAndView;
    }

    /**
     *
     * @param event
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public
    @ResponseBody
    ModelAndView create(@RequestBody Event event) {
        Event resultEvent = eventService.create(event);
        List<Event> events = new ArrayList<>();
        events.add(resultEvent);
        return GetModelView(events);
    }

    /**
     *
     * @param event
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public
    @ResponseBody
    ModelAndView remove(@RequestBody Event event) {
        eventService.remove(event);
        List<Event> events = new ArrayList<>();
        return GetModelView(events);
    }

    /**
     *
     * @param name
     * @param auditorium
     * @param dateTime
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/event/")
    public
    @ResponseBody
    ModelAndView getEvent(@ModelAttribute("name") String name,
                          @ModelAttribute("auditorium") Auditorium auditorium,
                          @ModelAttribute("dateTime") LocalDateTime dateTime) {
        Event event = eventService.getEvent(name, auditorium, dateTime);
        List<Event> events = new ArrayList<>();
        events.add(event);
        return GetModelView(events);
    }

    /**
     *
     * @param name
     * @return

     */
    @RequestMapping(method = RequestMethod.GET, value = "name/{name}")
    public
    @ResponseBody
    ModelAndView getByName(@PathVariable("name") String name) {
        List<Event> events = eventService.getByName(name);
        return GetModelView(events);
    }

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public
    @ResponseBody
    ModelAndView getAll() {
        List<Event> events = eventService.getAll();
        return GetModelView(events);
    }

    /**
     *
     * @param from
     * @param to
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/range/")
    public
    @ResponseBody
    ModelAndView getForDateRange(@ModelAttribute("from") LocalDateTime from,
                                 @ModelAttribute("to") LocalDateTime to) {
        List<Event> events = eventService.getForDateRange(from, to);
        return GetModelView(events);
    }

    /**
     *
     * @param to
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/next/{to}")
    public
    @ResponseBody
    ModelAndView getNextEvents(@PathVariable("to") LocalDateTime to) {
        List<Event> events = eventService.getNextEvents(to);
        return GetModelView(events);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/assign/auditorium/")
    public
    @ResponseBody
    ModelAndView assignAuditorium(@ModelAttribute("event") Event event,
                                  @ModelAttribute("auditorium") Auditorium auditorium,
                                  @ModelAttribute("date") LocalDateTime date) {
        Event resultEvent = eventService.assignAuditorium(event, auditorium, date);
        List<Event> events = new ArrayList<>();
        events.add(resultEvent);
        return GetModelView(events);
    }
}
