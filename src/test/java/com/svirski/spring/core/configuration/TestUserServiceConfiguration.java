package com.svirski.spring.core.configuration;

import com.svirski.spring.core.daos.UserDAO;
import com.svirski.spring.core.daos.mocks.IUserDAOMock;
import com.svirski.spring.core.daos.mocks.UserDAOMock;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.UserService;
import com.svirski.spring.core.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/12/2016
 * Time: 1:36 PM
 */
@Configuration
public class TestUserServiceConfiguration {

    @Bean
    public User testUser1() {
        return new User(0, "dmitriy.vbabichev@gmail.com", "Dmytro Babichev", java.time.LocalDate.of(1992, 4, 29), "1","BOOKING_MANAGER");
    }

    @Bean
    public User testUser2() {
        return new User(1, "laory@yandex.ru", "Dmytro Babichev", java.time.LocalDate.of(1992, 4, 29), "1","BOOKING_MANAGER");
    }

    @Bean(name = "testUserDAOImpl")
    public IUserDAOMock userDAO() {
        return new UserDAOMock(Arrays.asList(testUser1(), testUser2()));
    }

    @Bean(name = "testUserServiceImpl")
    public UserService userServiceImpl() {
        return new UserServiceImpl((UserDAO) userDAO());
    }
}
