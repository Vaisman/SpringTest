package com.svirski.spring.core.services.discount;

import com.svirski.spring.core.daos.mocks.BookingDAODiscountMock;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.discount.TicketsStrategy;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 06/2/16
 * Time: 2:16 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.svirski.spring.core.configuration.TestStrategiesConfiguration.class)
public class TicketsStrategyTest {

    @Autowired
    private TicketsStrategy strategy;

    @Autowired
    private BookingDAODiscountMock bookingDAODiscountMock;

    @org.junit.Test
    public void testCalculateDiscount_UserHasDiscount() throws Exception {
        System.out.println(strategy.getClass());
        User userWithDiscount = new User("test@ema.il", bookingDAODiscountMock.userThatBookedTickets, LocalDate.now(), "1","BOOKING_MANAGER");
        double discount = strategy.calculateDiscount(userWithDiscount);
        assertEquals("User: [" + userWithDiscount + "] has tickets discount", strategy.ticketsDiscountValue, discount, 0.00001);
    }

    @org.junit.Test
    public void testCalculateDiscount_UserHasNoDiscount() throws Exception {
        User userWithoutDiscount = new User("test@ema.il", "Test Name 2", LocalDate.now().minus(1, ChronoUnit.DAYS), "1","BOOKING_MANAGER");
        double discount = strategy.calculateDiscount(userWithoutDiscount);
        assertEquals("User: [" + userWithoutDiscount + "] doesn't have tickets discount", strategy.defaultDiscount, discount, 0.00001);
    }
}
