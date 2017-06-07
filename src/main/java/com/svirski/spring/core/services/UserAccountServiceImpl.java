package com.svirski.spring.core.services;

import java.util.List;

import com.svirski.spring.core.daos.UserAccountDAO;
import com.svirski.spring.core.daos.UserDAO;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Vasili_Svirski on 6/7/2017.
 */
public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountDAO userAccountDAO;

    @Autowired
    public UserAccountServiceImpl(@Qualifier("userAccountDAO") UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public UserAccount getByUser(User user) {
        List<UserAccount> userAccountsForUser = userAccountDAO.getUserAccountsForUser(user);
        return userAccountsForUser.get(0);
     }

    /**
     *
     * @param userAccount
     */
    @Override
    public void create(UserAccount userAccount) {
        userAccountDAO.add(userAccount);
    }

    /**
     *
     * @param userAccount
     */
    @Override
    public void update(UserAccount userAccount) {
        userAccountDAO.update(userAccount);
    }

    /**
     *
     * @param user
     * @param amount
     */
    @Override
    public void refillUserAccount(User user, Double amount) {
        UserAccount userAccount = getByUser(user);
        Double prepaidBalanceAfterTransaction = userAccount.getPrepaidBalance() + amount;
        userAccount.setPrepaidBalance(prepaidBalanceAfterTransaction);
        userAccountDAO.update(userAccount);
    }
}
