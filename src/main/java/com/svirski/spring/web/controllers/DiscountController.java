package com.svirski.spring.web.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.svirski.spring.core.models.Event;
import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.BookingService;
import com.svirski.spring.core.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
     * @param user
     * @param event
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/value/")
    public
    @ResponseBody
    ModelAndView getDiscount(User user, Event event) {
        Double discount = discountService.getDiscount(user, event);
        List<Double> discounts = new ArrayList<>();
        discounts.add(discount);
        return GetModelView(discounts);
    }
}
