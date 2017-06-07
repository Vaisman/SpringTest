package com.svirski.spring.core.daos.db;

import java.util.List;
import java.util.Objects;

import com.svirski.spring.core.daos.AbstractDAO;
import com.svirski.spring.core.daos.UserAccountDAO;
import com.svirski.spring.core.daos.UserDAO;
import com.svirski.spring.core.models.Event;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.models.UserAccount;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vasili_Svirski on 6/7/2017.
 */
@Repository(value = "userAccountDAO")
@Transactional
public class UserAccountDAOImpl extends AbstractDAO implements UserAccountDAO {

    @Override
    public UserAccount add(UserAccount userAccount) {
        UserAccountDAO.validateUserAccount(userAccount);

        int userAccountId = (int) getCurrentSession().save(userAccount);
        return userAccount.withId(userAccountId);
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        UserAccountDAO.validateUserAccount(userAccount);
        return ((UserAccount) getCurrentSession().merge(userAccount));
    }

    @Override
    public List<UserAccount> getUserAccountsForUser(User user) {
        return (List<UserAccount>) createBlankCriteria(UserAccount.class).add(Restrictions.eq("userId", (int) user.getId())).list();

    }
}
