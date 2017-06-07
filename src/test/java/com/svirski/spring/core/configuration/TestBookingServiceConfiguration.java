package com.svirski.spring.core.configuration;

import com.svirski.spring.core.daos.AuditoriumDAO;
import com.svirski.spring.core.daos.BookingDAO;
import com.svirski.spring.core.daos.EventDAO;
import com.svirski.spring.core.daos.UserAccountDAO;
import com.svirski.spring.core.daos.UserDAO;
import com.svirski.spring.core.daos.mocks.*;
import com.svirski.spring.core.models.*;
import com.svirski.spring.core.services.*;
import com.svirski.spring.core.services.discount.BirthdayStrategy;
import com.svirski.spring.core.services.discount.DiscountStrategy;
import com.svirski.spring.core.services.discount.TicketsStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 13/2/16
 * Time: 3:36 PM
 */
@Configuration
public class TestBookingServiceConfiguration {

    @Bean
    public DiscountStrategy birthdayBookingStrategy() {
        return new BirthdayStrategy(0.15, 0);
    }

    @Bean
    public DiscountStrategy ticketsBookingStrategy() {
        return new TicketsStrategy(bookingBookingDAO(), 0.5, 3, 0);
    }

    @Bean
    public BookingDAO bookingBookingDAO() {
        HashSet<Ticket> tickets = new HashSet<Ticket>() {
            {
                addAll(tickets());
            }
        };
        return new BookingDAOBookingMock(new HashMap<User, Set<Ticket>>() {
            {
                put(testUser1(), tickets);
            }
        });
    }

    @Bean
    public DiscountService discountBookingServiceImpl() {
        return new DiscountServiceImpl(Arrays.asList(birthdayBookingStrategy(), ticketsBookingStrategy()));
    }

    @Bean
    public EventDAO eventDAOMock() {
        return new EventDAOMock(Arrays.asList(testEvent1(), testEvent2()));
    }

    @Bean
    public EventService eventServiceImpl() {
        return new EventServiceImpl(eventDAOMock());
    }

    @Bean
    public Event testEvent1() {
        return new Event(1, "Test event", com.svirski.spring.core.models.Rate.HIGH, 124.0, java.time.LocalDateTime.of(2016, 2, 6, 14, 45, 0),
                         testHall1(), 1);
    }

    @Bean
    public Event testEvent2() {
        return new Event(2, "Test event2", Rate.MID, 500.0, java.time.LocalDateTime.of(2016, 12, 6, 9, 35, 0),
                         testHall2(), 1);
    }

    @Bean
    public User testUser1() {
        return new User(0, "dmitriy.vbabichev@gmail.com", "Dmytro Babichev", java.time.LocalDate.of(1992, 4, 29), "1","BOOKING_MANAGER");
    }

    @Bean
    public User testUser2() {
        return new User(1, "laory@yandex.ru", "Dmytro Babichev", java.time.LocalDate.of(1992, 4, 29), "1","BOOKING_MANAGER");
    }

    @Bean
    public Ticket testTicket1() {
        return new Ticket(1, testEvent1(), java.time.LocalDateTime.of(2016, 2, 6, 14, 45, 0), Arrays.asList(3, 4),
                          testUser1(), 32D);
    }

    @Bean
    public UserAccount testUserAccount1() {
        return new UserAccount(1, 1, 1.);
    }

    @Bean
    public UserAccount testUserAccount2() {
        return new UserAccount(2, 2, 2000.);
    }

    @Bean
    public UserAccount testUserAccount3() {
        return new UserAccount(3, 3, 1000.);
    }

    @Bean
    public UserAccount testUserAccount4() {
        return new UserAccount(4, 4, 1000.);
    }

    @Bean
    public UserAccount testUserAccount5() {
        return new UserAccount(5, 5, 1000.);
    }

    @Bean
    public UserAccount testUserAccount6() {
        return new UserAccount(6, 6, 1000.);
    }

    @Bean
    public UserAccount testUserAccount7() {
        return new UserAccount(10, 7, 1000.);
    }

    @Bean
    public UserAccount testUserAccount8() {
        return new UserAccount(11, 8, 1000.);
    }

    @Bean
    public UserAccount testUserAccount12() {
        return new UserAccount(7, 12, 1000.0);
    }

    @Bean
    public UserAccount testUserAccount14() {
        return new UserAccount(9, 14, 1000.);
    }

    @Bean
    public UserAccount testUserAccount11() {
        return new UserAccount(8, 11, 1000.);
    }

    @Bean
    public Ticket testTicket2() {
        return new Ticket(2, testEvent2(), java.time.LocalDateTime.of(2016, 2, 7, 14, 45, 0), Arrays.asList(1, 2),
                          testUser1(), 123D);
    }

    @Bean
    public List<Ticket> tickets() {
        return Arrays.asList(testTicket1(), testTicket2());
    }

    @Bean
    public List<UserAccount> userAccounts() {
        return Arrays.asList(
                testUserAccount1(),
                testUserAccount2(),
                testUserAccount3(),
                testUserAccount4(),
                testUserAccount5(),
                testUserAccount6(),
                testUserAccount7(),
                testUserAccount8(),
                testUserAccount11(),
                testUserAccount12(),
                testUserAccount14()
                );
    }

    @Bean
    public Auditorium testHall1() {
        return new Auditorium(1, "Test auditorium", 15, Arrays.asList(1, 2, 3, 4, 5));
    }

    @Bean
    public Auditorium testHall2() {
        return new Auditorium(2, "Test auditorium 2", 8, Collections.singletonList(1));
    }

    @Bean
    public AuditoriumDAO auditoriumDAO() {
        return new DBAuditoriumDAOMock(Arrays.asList(testHall1(), testHall2()));
    }

    @Bean
    public AuditoriumService auditoriumServiceImpl() {
        return new AuditoriumServiceImpl(auditoriumDAO());
    }

    @Bean(name = "testUserDAOImpl")
    public IUserDAOMock userDAOMock() {
        return new UserDAOMock(Arrays.asList(testUser1()));
    }

    @Bean
    public UserService userServiceImpl() {
        return new UserServiceImpl((UserDAO) userDAOMock());
    }

    @Bean(name = "testUserAccountDAOImpl")
    public UserAccountDAO userAccountDAOMock() {
        List<UserAccount> userAccounts = new LinkedList<UserAccount>() {
            {
                addAll(userAccounts());
            }
        };
        return new UserAccountDAOMock(userAccounts);
    }

    @Bean
    public UserAccountService userAccountServiceImpl() {
        return new UserAccountServiceImpl((UserAccountDAO)userAccountDAOMock() );
    }

    @Bean(name = "testBookingServiceImpl")
    public BookingService bookingServiceImpl() {
        return new BookingServiceImpl(eventServiceImpl(), auditoriumServiceImpl(), userServiceImpl(),
                                      discountBookingServiceImpl(), userAccountServiceImpl(), bookingBookingDAO(),
                1, 2, 1.2, 1);
    }
}
