package com.svirski.spring.web.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vasili_Svirski on 5/29/2017.
 */
@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    private String bookingPageName = "bookingPage";
    private String objectsListName = "objectsList";

    /**
     *
     * @param list
     * @return
     */
    private ModelAndView GetModelView(List<?> list) {
        ModelAndView modelAndView = new ModelAndView(bookingPageName);
        modelAndView.addObject(objectsListName, list);
        return modelAndView;
    }

    /**
     *
     * @param event
     * @param auditorium
     * @param dateTime
     * @param seats
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/ticket/price/")
    public
    @ResponseBody
    ModelAndView getTicketPrice(String event, String auditorium, LocalDateTime dateTime, List<Integer> seats, User user) {
        Double price = bookingService.getTicketPrice(event, auditorium, dateTime, seats, user);
        List<Double> prices = new ArrayList<>();
        prices.add(price);
        return GetModelView(prices);
    }

    /**
     *
     * @param user
     * @param ticket
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/book/")
    public
    @ResponseBody
    ModelAndView bookTicket(User user, Ticket ticket) {
        Ticket resultTicket = bookingService.bookTicket(user, ticket);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        return GetModelView(tickets);
    }

    /**
     *
     * @param event
     * @param auditorium
     * @param date
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces="application/pdf", value = "/tickets/for/event/")
    public
    @ResponseBody
    ModelAndView getTicketsForEvent(@ModelAttribute("event") String event, @ModelAttribute("auditorium") String auditorium, @ModelAttribute("date")  LocalDateTime date) {
        List<Ticket> tickets = bookingService.getTicketsForEvent(event, auditorium, date);
        ModelAndView modelAndView = new ModelAndView("ticketListPagePdfView");
        modelAndView.addObject("ticketList", tickets);
        return modelAndView;
    }
}
