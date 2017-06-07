package com.svirski.spring.core.daos.mocks;

import java.util.List;

import com.svirski.spring.core.daos.db.UserAccountDAOImpl;
import com.svirski.spring.core.daos.db.UserDAOImpl;
import com.svirski.spring.core.models.User;
import com.svirski.spring.core.models.UserAccount;

/**
 * Created by Vasili_Svirski on 6/13/2017.
 */
public class UserAccountDAOMock extends UserAccountDAOImpl implements IUserAccountDAOMock {

    private final List<UserAccount> usersAccount;

    public UserAccountDAOMock(List<UserAccount> usersAccount) {
        this.usersAccount = usersAccount;
    }

    public void init() {
        usersAccount.forEach(this :: add);
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        return super.add(userAccount);
    }
}
