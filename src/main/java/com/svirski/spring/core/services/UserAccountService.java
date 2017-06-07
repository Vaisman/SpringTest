package com.svirski.spring.core.services;

import com.svirski.spring.core.models.User;
import com.svirski.spring.core.models.UserAccount;

/**
 * Created by Vasili_Svirski on 6/7/2017.
 */
public interface UserAccountService {

    /**
     *
     * @param user
     * @return
     */
    UserAccount getByUser(User user);

    /**
     *
     * @param userAccount
     */
    void create(UserAccount userAccount);

    /**
     *
     * @param userAccount
     */
    void update(UserAccount userAccount);

    /**
     *
     * @param user
     * @param amount
     */
    void refillUserAccount(User user, Double amount);
}
