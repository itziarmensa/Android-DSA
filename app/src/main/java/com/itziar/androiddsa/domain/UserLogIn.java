package com.itziar.androiddsa.domain;

import com.itziar.androiddsa.domain.vo.Credentials;

public class UserLogIn {
    Credentials credentials;

    public UserLogIn(){}

    public UserLogIn(Credentials credentials) {
        this.credentials = credentials;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
