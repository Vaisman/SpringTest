package com.svirski.spring.core.daos.mocks;

import com.svirski.spring.core.models.User;

/**
 * Created by Vasili_Svirski on 6/7/2017.
 */
public interface IUserDAOMock {
    void init() ;
    void cleanup();
    User create(User user);
}
