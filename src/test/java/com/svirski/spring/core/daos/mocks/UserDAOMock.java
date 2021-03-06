package com.svirski.spring.core.daos.mocks;

import com.svirski.spring.core.daos.UserDAO;
import com.svirski.spring.core.daos.db.UserDAOImpl;
import com.svirski.spring.core.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 06/2/16
 * Time: 2:41 PM
 */

public class UserDAOMock extends UserDAOImpl implements IUserDAOMock {

    private final List<User> users;

    public UserDAOMock(List<User> users) {
        this.users = users;
    }

    public void init() {
        cleanup();
        users.forEach(this :: create);
    }

    public void cleanup() {
        getAll().forEach(this :: delete);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }
}
