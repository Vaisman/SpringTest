package com.svirski.spring.core.daos.db;

import com.svirski.spring.core.daos.AbstractDAO;
import com.svirski.spring.core.daos.UserDAO;
import com.svirski.spring.core.models.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 20/2/16
 * Time: 4:35 PM
 */
@Repository(value = "userDAO")
@Transactional
public class UserDAOImpl extends AbstractDAO implements UserDAO {

    @Override
    public User create(User user) {
        UserDAO.validateUser(user);
        User byEmail = getByEmail(user.getEmail());
        if (Objects.nonNull(byEmail)) {
            throw new IllegalStateException(
                    String.format("Unable to store user: [%s]. User with email: [%s] is already created.", user,
                                  user.getEmail()));
        } else {
            Long userId = (Long) getCurrentSession().save(user);
            return user.withId(userId);
        }
    }

    @Override
    public void delete(User user) {
        UserDAO.validateUser(user);
        getCurrentSession().delete(user);
    }

    @Override
    public User get(long id) {
        return getCurrentSession().get(User.class, id);
    }

    @Override
    public User getByEmail(String email) {
        return ((User) createBlankCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllByName(String name) {
        return createBlankCriteria(User.class).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return ((List<User>) createBlankCriteria(User.class).list());
    }
}
