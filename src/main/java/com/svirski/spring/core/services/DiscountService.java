package com.svirski.spring.core.services;

import com.svirski.spring.core.models.Event;
import com.svirski.spring.core.models.User;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/4/2016
 * Time: 11:17 AM
 */
public interface DiscountService {

    double getDiscount(User user, Event event);
}
