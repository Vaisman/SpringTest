package com.svirski.spring.core.daos.mocks;

import com.svirski.spring.core.daos.BookingDAO;
import com.svirski.spring.core.models.Event;
import com.svirski.spring.core.models.Ticket;
import com.svirski.spring.core.models.User;

import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 06/2/16
 * Time: 3:08 AM
 */
public class BookingDAODiscountMock implements BookingDAO {

    public final String userThatBookedTickets;
    public final int    ticketsBought;

    public BookingDAODiscountMock(String userThatBookedTickets, int ticketsBought) {
        this.userThatBookedTickets = userThatBookedTickets;
        this.ticketsBought = ticketsBought;
    }

    @Override
    public Ticket create(User user, Ticket ticket) {
        return null;
    }

    @Override
    public void delete(User user, Ticket booking) {

    }

    @Override
    public List<Ticket> getTickets(Event event) {
        return null;
    }

    @Override
    public List<Ticket> getTickets(User user) {
        return null;
    }

    @Override
    public long countTickets(User user) {
        return Objects.equals(user.getName(), userThatBookedTickets) ? ticketsBought : 0;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }
}
