package com.svirski.spring.core.configuration;

import com.svirski.spring.core.daos.BookingDAO;
import com.svirski.spring.core.daos.mocks.BookingDAODiscountMock;
import com.svirski.spring.core.services.DiscountService;
import com.svirski.spring.core.services.DiscountServiceImpl;
import com.svirski.spring.core.services.discount.BirthdayStrategy;
import com.svirski.spring.core.services.discount.TicketsStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 13/2/16
 * Time: 3:36 PM
 */
@Configuration
public class TestStrategiesConfiguration {

    @Bean
    public BirthdayStrategy birthdayStrategy() {
        return new BirthdayStrategy(1.0, 0);
    }

    @Bean
    public TicketsStrategy ticketsStrategy() {
        return new TicketsStrategy(bookingDiscountDAO(), 0.5, 2, 0);
    }

    @Bean
    public BookingDAO bookingDiscountDAO() {
        return new BookingDAODiscountMock("Test User", 1);
    }

    @Bean
    public DiscountService discountServiceImpl() {
        return new DiscountServiceImpl(Arrays.asList(birthdayStrategy(), ticketsStrategy()));
    }
}
