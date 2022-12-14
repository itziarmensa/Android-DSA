package com.grupo3.androiddsa.domain.to;


import com.grupo3.androiddsa.domain.vo.Credentials;

import java.io.Serializable;

public class UserRegister implements Serializable {
    String userName;
    String userSurname;
    String birthDate;
    String mail;
    String password;

    public UserRegister() {
    }

    public UserRegister(String userName, String userSurname, String birthDate, String mail, String password) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.birthDate = birthDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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