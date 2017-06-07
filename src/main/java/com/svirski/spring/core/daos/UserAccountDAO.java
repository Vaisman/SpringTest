package com.svirski.spring.core.daos;

import java.util.List;
import java.util.Objects;

import com.svirski.spring.core.models.User;
import com.svirski.spring.core.models.UserAccount;

/**
 * Created by Vasili_Svirski on 6/7/2017.
 */
public interface UserAccountDAO {

    UserAccount add(UserAccount userAccount);

    UserAccount update(UserAccount userAccount);

    List<UserAccount> getUserAccountsForUser(User user);

    static void validateUserAccount(UserAccount userAccount) {
        if (Objects.isNull(userAccount)) {
            throw new NullPointerException("User is [null]");
        }
        if (Objects.isNull(userAccount.getUserId())) {
            throw new NullPointerException("User id is [null]. userAccount: [" + userAccount + "]");
        }
        if (Objects.isNull(userAccount.getPrepaidBalance ())) {
            throw new NullPointerException("User account balance is [null]. userAccount: [" + userAccount + "]");
        }
    }
}
