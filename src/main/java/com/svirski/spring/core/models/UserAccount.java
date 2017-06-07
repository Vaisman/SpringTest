package com.svirski.spring.core.models;

/**
 * Created by Vasili_Svirski on 6/7/2017.
 */
public class UserAccount {

    private int id;
    private int userId;
    private Double prepaidBalance;

    public UserAccount() {
    }

    public UserAccount(int id, int userId, Double prepaidBalance) {
        this.id = id;
        this.userId = userId;
        this.prepaidBalance = prepaidBalance;
    }

    public UserAccount withId(int id) {
        return new UserAccount(id, userId, prepaidBalance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getPrepaidBalance() {
        return prepaidBalance;
    }

    public void setPrepaidBalance(Double prepaidBalance) {
        this.prepaidBalance = prepaidBalance;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", userId=" + userId +
                ", prepaidBalance=" + prepaidBalance +
                '}';
    }
}
