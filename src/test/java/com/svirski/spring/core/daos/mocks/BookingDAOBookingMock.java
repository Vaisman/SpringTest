package com.svirski.spring.core.daos.mocks;

import com.svirski.spring.core.daos.db.BookingDAOImpl;
import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;

import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 06/2/16
 * Time: 3:08 AM
 */
public class BookingDAOBookingMock extends BookingDAOImpl {

    private final Map<User, Set<Ticket>> initWith;

    public BookingDAOBookingMock(Map<User, Set<Ticket>> initWith) {
        this.initWith = initWith;
    }

    public void init() {
        cleanup();
        System.out.println("creating " + initWith);
        initWith.forEach((user, tickets) -> tickets.forEach(ticket -> create(user, ticket)));
    }

    public void cleanup() {getAllTickets().forEach(ticket -> delete(ticket.getUser(), ticket));}
}
