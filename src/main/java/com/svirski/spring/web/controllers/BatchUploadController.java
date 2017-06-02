package com.svirski.spring.web.controllers;

import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.BookingService;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasili_Svirski on 6/2/2017.
 */
@Controller
@RequestMapping("/batch")
public class BatchUploadController {

    @Autowired
    private BookingService bookingService;

    /**
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/tickets/book")
    public ModelAndView bookTicket(@RequestParam("file") MultipartFile file)
            throws Exception {

        List<Pair<User, Ticket>> usersTickets = parseJsonFile(file);

        List<Ticket> tickets = new ArrayList<>();
        for (Pair<User, Ticket> pair : usersTickets) {
            Ticket ticket = bookingService.bookTicket(pair.getKey(), pair.getValue());
            tickets.add(ticket);
        }

        ModelAndView modelAndView = new ModelAndView("bookingPage");
        modelAndView.addObject("objectsList", tickets);
        return modelAndView;
    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     * @throws ParseException
     */
    private List<Pair<User, Ticket>> parseJsonFile(MultipartFile file) throws IOException, ParseException {
        List<Pair<User, Ticket>> usersTickets = new ArrayList<>();

        if (!file.isEmpty()) {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(file.getInputStream(), "UTF-8"));

            JSONArray usersTicketArray = (JSONArray) jsonObject.get("pairsArray");

            for (Object userTicketObject : usersTicketArray.toArray()){
                Ticket ticket = new Ticket();
                User user = new User();
                JSONObject ticketJsonObject = (JSONObject) userTicketObject;

                user.setId(Long.parseLong(ticketJsonObject.get("userId").toString()));
                ticket.setId(Long.parseLong(ticketJsonObject.get("ticketId").toString()));

                usersTickets.add(new Pair<>(user, ticket));
            }
        }
        return usersTickets;
    }
}
