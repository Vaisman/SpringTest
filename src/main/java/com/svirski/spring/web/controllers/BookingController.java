package com.svirski.spring.web.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svirski.spring.core.models.Auditorium;
import com.svirski.spring.core.models.Event;
import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    ModelAndView getTicketPrice(@ModelAttribute("event") String event,
                                @ModelAttribute("auditorium") String auditorium,
                                @ModelAttribute("dateTime") LocalDateTime dateTime,
                                @ModelAttribute("seats") List<Integer> seats,
                                @ModelAttribute("user") User user) {

        Double price = bookingService.getTicketPrice(event, auditorium, dateTime, seats, user);
        List<Double> prices = new ArrayList<>();
        prices.add(price);
        return GetModelView(prices);
    }

    /**
     *
     * @param info
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/book/")
    public
    @ResponseBody
    ModelAndView bookTicket(@RequestBody String info) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(info);
        JsonNode userNode = root.path("User");
        JsonNode ticketNode = root.path("Ticket");
        User user = mapper.readValue(userNode.toString(), User.class);
        Ticket ticket = mapper.readValue(ticketNode.toString(), Ticket.class);

        Ticket resultTicket = bookingService.bookTicket(user, ticket);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        return GetModelView(tickets);
    }

    /**
     *
     * @param info
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, produces="application/pdf", value = "/tickets/for/event/")
    public
    @ResponseBody
    ModelAndView getTicketsForEvent(@RequestBody String info) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(info);

        String auditorium = root.path("Auditorium").asText();
        String event = root.path("Event").asText();

        String date = root.path("Date").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        List<Ticket> tickets = bookingService.getTicketsForEvent(event, auditorium, dateTime);
        ModelAndView modelAndView = new ModelAndView("ticketListPagePdfView");
        modelAndView.addObject("ticketList", tickets);
        return modelAndView;
    }
}
