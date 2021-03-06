import com.svirski.spring.core.aspects.CounterAspect;
import com.svirski.spring.core.aspects.DiscountAspect;
import com.svirski.spring.core.aspects.LuckyWinnerAspect;

import com.svirski.spring.core.models.Auditorium;
import com.svirski.spring.core.models.Event;
import com.svirski.spring.core.models.Rate;
import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.models.UserAccount;
import com.svirski.spring.core.services.AuditoriumService;
import com.svirski.spring.core.services.BookingService;
import com.svirski.spring.core.services.DiscountService;
import com.svirski.spring.core.services.EventService;
import com.svirski.spring.core.services.UserAccountService;
import com.svirski.spring.core.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/4/2016
 * Time: 12:06 PM
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        AuditoriumService auditoriumService = (AuditoriumService) ctx.getBean("auditoriumServiceImpl");
        BookingService bookingService = (BookingService) ctx.getBean("bookingServiceImpl");
        EventService eventService = (EventService) ctx.getBean("eventServiceImpl");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        DiscountService discountService = (DiscountService) ctx.getBean("discountServiceImpl");
        UserAccountService userAccountService = (UserAccountService) ctx.getBean("userAccountServiceImpl");

        String email = "dmitriy.vbabichev@gmail.com";
        String name = "Dmytro Babichev";
        String eventName = "The revenant";
        String auditoriumName = "Blue hall";
        Auditorium blueHall = auditoriumService.getByName(auditoriumName);
        Auditorium yellowHall = auditoriumService.getByName("Yellow hall");
        Auditorium redHall = auditoriumService.getByName("Red hall");
        LocalDateTime dateOfEvent = LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(15, 45, 0));

        userService.register(new User(email, name, LocalDate.now(), "1", "REGISTERED_USER,BOOKING_MANAGER" ));
        userService.register(new User("laory@yandex.ru", name, LocalDate.of(1992, 4, 29), "2", "REGISTERED_USER" ));

        User userByEmail = userService.getUserByEmail(email);
        System.out.println("User with email: [" + email + "] is " + userByEmail);
        System.out.println();

        System.out.println("All users with name: [" + name + "] are: ");
        userService.getUsersByName(name).forEach(System.out:: println);
        System.out.println();

        Event event1 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(9, 0, 0)),
                          blueHall, 1));
        System.out.println();
        System.out.println("Event by name: " + eventService.getByName(event1.getName()));
        System.out.println();
        eventService.create(new Event(eventName, Rate.HIGH, 60, dateOfEvent, blueHall, 1));
        Event event2 = eventService.create(
                new Event(eventName, Rate.HIGH, 60, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                          blueHall, 1));
        eventService.create(
                new Event(eventName, Rate.HIGH, 90, LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)),
                          redHall, 1));
        Event event = new Event(eventName, Rate.HIGH, 150,
                                LocalDateTime.of(LocalDate.of(2016, 2, 5), LocalTime.of(21, 18, 0)), yellowHall, 1);
        event = eventService.create(event);

        System.out.println("List of all events:");
        eventService.getAll().forEach(System.out:: println);
        System.out.println();

        System.out.println(
                "Discount for user: [" + email + "] for event: [" + eventName + "] in auditorium: [" + auditoriumName +
                "] on date: [" + dateOfEvent + "] is [" +
                discountService.getDiscount(userByEmail, eventService.getEvent(eventName, blueHall, dateOfEvent))
                + "]");
        System.out.println();

        eventService.remove(event2);
        System.out.println("List of all events:");
        eventService.getAll().forEach(System.out:: println);
        System.out.println();

        List<Integer> seats = Arrays.asList(23, 24, 25, 26);
        double ticketPrice = bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(),
                                                           event.getDateTime(), seats, userByEmail);
        System.out.println("Price for event: [" + event + "], seats: [" + seats + "], user: [" + userByEmail + "] = "
                           + ticketPrice);

        List<Integer> seats2 = Arrays.asList(27, 28, 29, 30);
        List<Integer> seats3 = Arrays.asList(2, 8, 9, 3);
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats, userByEmail, ticketPrice));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats2, userByEmail,
                                                          bookingService.getTicketPrice(event.getName(),
                                                                                        event.getAuditorium().getName(),
                                                                                        event.getDateTime(), seats2,
                                                                                        userByEmail)));
        bookingService.bookTicket(userByEmail, new Ticket(event, LocalDateTime.now(), seats3, userByEmail,
                                                          bookingService.getTicketPrice(event.getName(),
                                                                                        event.getAuditorium().getName(),
                                                                                        event.getDateTime(), seats3,
                                                                                        userByEmail)));

        System.out.println();
        System.out.println("Tickets booked for event: [" + event + "]");
        List<Ticket> ticketsForEvent = bookingService.getTicketsForEvent(event.getName(),
                                                                         event.getAuditorium().getName(),
                                                                         event.getDateTime());
        IntStream.range(0, ticketsForEvent.size()).forEach(
                i -> System.out.println("" + i + ") " + ticketsForEvent.get(i)));

        System.out.println();
        eventService.getByName("testName1");
        eventService.getByName("testName2");
        eventService.getByName("testName2");
        eventService.getByName("testName3");
        eventService.getByName(eventName);
        eventService.getEvent(event.getName(), event.getAuditorium(), event.getDateTime());

        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                                      userByEmail);
        bookingService.getTicketPrice(event.getName(), event.getAuditorium().getName(), event.getDateTime(), seats,
                                      userByEmail);

        System.out.println("CounterAspect.getAccessByNameStat() = " + CounterAspect.getAccessByNameStat());
        System.out.println("CounterAspect.getGetPriceByNameStat() = " + CounterAspect.getGetPriceByNameStat());
        System.out.println("CounterAspect.getBookTicketByNameStat() = " + CounterAspect.getBookTicketByNameStat());
        System.out.println();
        System.out.println("DiscountAspect.getDiscountStatistics() = " + DiscountAspect.getDiscountStatistics());
        System.out.println();
        System.out.println("LuckyWinnerAspect.getLuckyUsers() = " + LuckyWinnerAspect.getLuckyUsers());

        userAccountService.create(new UserAccount(0, 0, 0.));
        userAccountService.create(new UserAccount(1, 1, 1.));
        userAccountService.getByUser( new User(1, "","",  LocalDate.now(), "",""));
    }
}
