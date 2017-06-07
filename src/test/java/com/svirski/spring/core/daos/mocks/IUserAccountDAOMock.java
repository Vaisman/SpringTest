package com.svirski.spring.core.daos.mocks;

import com.svirski.spring.core.models.User;
import com.svirski.spring.core.models.UserAccount;

/**
 * Created by Vasili_Svirski on 6/13/2017.
 */
public interface IUserAccountDAOMock {
    void init() ;
    UserAccount create(UserAccount userAccount);
}