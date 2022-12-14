package com.grupo3.androiddsa.domain;

import com.grupo3.androiddsa.domain.vo.Credentials;
import com.grupo3.androiddsa.domain.vo.RandomId;

import java.io.Serializable;

public class User implements Serializable{
    String userId;
    String userName;
    String userSurname;
    String userBirth;
    String email;
    String password;
    public User()
    {

    }

    public User(String userName, String userSurname, String userBirth, String email, String password) {
        this.userId= RandomId.getId();
        this.userName = userName;
        this.userSurname = userSurname;
        this.userBirth = userBirth;
        this.email = email;
        this.password = password;
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
}
