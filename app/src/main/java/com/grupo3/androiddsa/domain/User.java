package com.grupo3.androiddsa.domain;

import com.grupo3.androiddsa.domain.vo.Credentials;
import com.grupo3.androiddsa.domain.vo.RandomId;

import java.io.Serializable;

public class User implements Serializable{
    String userId;
    String userName;
    String userSurname;
    String userBirth;
    double coins;
    int points;
    String email;
    String password;
    String language;
    public User()
    {

    }

    public User(String userName, String userSurname, String userBirth, double coins, int points, String email, String password, String language) {
        this.userId= RandomId.getId();
        this.userName = userName;
        this.userSurname = userSurname;
        this.userBirth = userBirth;
        this.coins = coins;
        this.points = points;
        this.email = email;
        this.password = password;
        this.language = language;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public double getCoins() {
        return this.coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
