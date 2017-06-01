package com.svirski.spring.core.services.discount;

import com.svirski.spring.core.models.User;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/4/2016
 * Time: 11:24 AM
 */
public interface DiscountStrategy {

    double calculateDiscount(User user);
}
