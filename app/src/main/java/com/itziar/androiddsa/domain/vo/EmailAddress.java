package com.itziar.androiddsa.domain.vo;

import java.util.Objects;

public class EmailAddress {
    String email;

    public EmailAddress() {
    }

    public EmailAddress(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEqual(EmailAddress emailAddress) {
        return (Objects.equals(email, emailAddress.getEmail()));
    }
}
