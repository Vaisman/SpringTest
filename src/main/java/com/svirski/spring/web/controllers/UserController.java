package com.svirski.spring.web.controllers;

import java.util.ArrayList;
import java.util.List;

import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vasili_Svirski on 5/29/2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private String usersPageName = "usersPage";
    private String objectsListName = "objectsList";

    private ModelAndView GetModelView(List<?> list) {
        ModelAndView modelAndView = new ModelAndView(usersPageName);
        modelAndView.addObject(objectsListName, list);
        return modelAndView;
    }

    /**
     *
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public
    @ResponseBody
    ModelAndView register(@ModelAttribute("user") User user) {
        User result = userService.register(user);
        List<User> users = new ArrayList<>();
        users.add(result);
        return GetModelView(users);
    }

    /**
     *
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public
    @ResponseBody
    ModelAndView remove(@RequestBody User user) {
        userService.remove(user);
        List<User> users = new ArrayList<>();
        users.add(new User());
        return GetModelView(users);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "id/{id}")
    public
    @ResponseBody
    ModelAndView getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        List<User> users = new ArrayList<>();
        users.add(user);
        return GetModelView(users);
    }

    /**
     *
     * @param email
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "email/{email}")
    public
    @ResponseBody
    ModelAndView getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        List<User> users = new ArrayList<>();
        users.add(user);
        return GetModelView(users);
    }

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "name/{name}")
    public
    @ResponseBody
    ModelAndView getUsersByName(@PathVariable("name") String name) {
        List<User> users = userService.getUsersByName(name);
        return GetModelView(users);
    }

    /**
     *
     * @return
     */
   @RequestMapping(method = RequestMethod.GET, produces="application/pdf", value = "/tickets/")
   public
   @ResponseBody
   ModelAndView getBookedTickets() {
       List<Ticket> tickets = userService.getBookedTickets();
       tickets.add(new Ticket());
       ModelAndView modelAndView = new ModelAndView("ticketListPagePdfView");
       modelAndView.addObject("ticketList", tickets);
       return modelAndView;
   }
}
