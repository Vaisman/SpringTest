package com.svirski.spring.core.daos.mocks;

import com.svirski.spring.core.daos.inmemory.InMemoryAuditoriumDAO;
import com.svirski.spring.core.models.Auditorium;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 06/2/16
 * Time: 1:27 PM
 */
public class InMemoryAuditoriumDAOMock extends InMemoryAuditoriumDAO {

    public InMemoryAuditoriumDAOMock(List<Auditorium> auditoriums) {
        super(auditoriums);
    }
}
