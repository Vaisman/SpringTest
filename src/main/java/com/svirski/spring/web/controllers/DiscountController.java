package com.svirski.spring.web.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.svirski.spring.core.models.Event;
import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.BookingService;
import com.svirski.spring.core.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vasili_Svirski on 5/29/2017.
 */
@Controller
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    private String discountPageName = "discountPage";
    private String objectsListName = "objectsList";

    /**
     *
     * @param list
     * @return
     */
    private ModelAndView GetModelView(List<?> list) {
        ModelAndView modelAndView = new ModelAndView(discountPageName);
        modelAndView.addObject(objectsListName, list);
        return modelAndView;
    }

    /**
     *
     * @param info
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/value/")
    public
    @ResponseBody
    ModelAndView getDiscount(@RequestBody String info) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(info);
        JsonNode userNode = root.path("User");
        JsonNode eventNode = root.path("Event");
        User user = mapper.readValue(userNode.toString(), User.class);
        Event event = mapper.readValue(eventNode.toString(), Event.class);

        Double discount = discountService.getDiscount(user, event);
        List<Double> discounts = new ArrayList<>();
        discounts.add(discount);
        return GetModelView(discounts);
    }
}
