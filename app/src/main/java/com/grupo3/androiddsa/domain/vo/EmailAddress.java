package com.grupo3.androiddsa.domain.vo;

import java.io.Serializable;
import java.util.Objects;

public class EmailAddress implements Serializable {
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
