package com.svirski.spring.core.aspects;

import com.svirski.spring.core.aspects.mocks.LuckyWinnerAspectMock;
import com.svirski.spring.core.configuration.AppConfiguration;
import com.svirski.spring.core.configuration.db.DataSourceConfiguration;
import com.svirski.spring.core.configuration.db.DbSessionFactory;
import com.svirski.spring.core.daos.mocks.BookingDAOBookingMock;
import com.svirski.spring.core.daos.mocks.DBAuditoriumDAOMock;
import com.svirski.spring.core.daos.mocks.EventDAOMock;
import com.svirski.spring.core.daos.mocks.IUserAccountDAOMock;
import com.svirski.spring.core.daos.mocks.IUserDAOMock;
import com.svirski.spring.core.daos.mocks.UserDAOMock;
import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.services.BookingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 13/2/16
 * Time: 7:20 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class, DataSourceConfiguration.class, DbSessionFactory.class,
        com.svirski.spring.core.configuration.TestAspectsConfiguration.class})
@Transactional
public class TestLuckyWinnerAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingDAOBookingMock bookingDAOBookingMock;

    @Autowired
    private EventDAOMock eventDAOMock;

    @Autowired
    @Value("#{testUserDAOImpl}")
    private IUserDAOMock userDAOMock;

    @Autowired
    private LuckyWinnerAspectMock luckyWinnerAspectMock;

    @Autowired
    private DBAuditoriumDAOMock auditoriumDAOMock;

    @Autowired()
    @Value("#{testUserAccountDAOImpl}")
    private IUserAccountDAOMock userAccountDAOMock;

    @Before
    public void init() {
        LuckyWinnerAspectMock.cleanup();
        auditoriumDAOMock.init();
        userDAOMock.init();
        eventDAOMock.init();
        bookingDAOBookingMock.init();
        userAccountDAOMock.init();
    }

    @After
    public void cleanup() {
        LuckyWinnerAspectMock.cleanup();
        auditoriumDAOMock.cleanup();
        userDAOMock.cleanup();
        eventDAOMock.cleanup();
        bookingDAOBookingMock.cleanup();
    }

    @Test
    public void testCalculateDiscount() {
        User user = (User) applicationContext.getBean("testUser1");
        User discountUser = new User(user.getId(), user.getEmail(), user.getName(), LocalDate.now(), "1","BOOKING_MANAGER");
        Ticket ticket1 = (Ticket) applicationContext.getBean("testTicket1");
        bookingService.bookTicket(discountUser,
                                  new Ticket(ticket1.getEvent(), ticket1.getDateTime(), Arrays.asList(5, 6), user, ticket1.getPrice()));
        bookingService.bookTicket(discountUser,
                                  new Ticket(ticket1.getEvent(), ticket1.getDateTime(), Arrays.asList(7, 8), user, ticket1.getPrice()));
        bookingService.bookTicket(discountUser,
                                  new Ticket(ticket1.getEvent(), ticket1.getDateTime(), Arrays.asList(9, 10), user, ticket1.getPrice()));
        bookingService.bookTicket(discountUser,
                                  new Ticket(ticket1.getEvent(), ticket1.getDateTime(), Arrays.asList(11, 12), user, ticket1.getPrice()));

        assertEquals(Collections.singletonList(user.getEmail()), LuckyWinnerAspectMock.getLuckyUsers());
    }
}
