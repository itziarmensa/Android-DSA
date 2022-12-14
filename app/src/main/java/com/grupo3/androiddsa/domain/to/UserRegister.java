package com.grupo3.androiddsa.domain.to;


import java.io.Serializable;

public class UserRegister implements Serializable {
    String userName;
    String userSurname;
    String userBirth;
    String mail;
    String password;

    public UserRegister() {
    }

    public UserRegister(String userName, String userSurname, String userBirth, String mail, String password) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userBirth = userBirth;
        this.mail=mail;
        this.password=password;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}