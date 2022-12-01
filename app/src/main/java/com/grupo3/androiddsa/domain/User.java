package com.grupo3.androiddsa.domain;

import com.grupo3.androiddsa.domain.vo.Credentials;
import com.grupo3.androiddsa.domain.vo.RandomId;

import java.io.Serializable;

public class User implements Serializable {
    String userName;
    String userSurname;
    String userBirth;
    Credentials credentials;
    String userId;

    public User() {

    }

    public User(String userName, String userSurname, String userBirth, Credentials credentials) {
        this.userId = RandomId.getId(); //En principio lo puse así para tener una Id aleatoria. Hay que hablar de si queremos que siga algún orden o algo
        this.userName = userName;
        this.userSurname = userSurname;
        this.userBirth = userBirth;
        this.credentials = credentials;
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

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean hasEmail(Credentials credentials) {
        return this.credentials.getEmail().isEqual(credentials.getEmail());
    }
}
