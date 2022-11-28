package com.itziar.androiddsa.domain.vo;

import com.itziar.androiddsa.domain.exceptions.EmailAddressNotValidException;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class EmailAddress {
    String email;
    public EmailAddress(){}
    public EmailAddress(String email) throws EmailAddressNotValidException {
        if(!EmailValidator.getInstance().isValid(email)){
            throw new EmailAddressNotValidException();
        }
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email) throws EmailAddressNotValidException{
        if(!EmailValidator.getInstance().isValid(email)){
            throw new EmailAddressNotValidException();
        }
        this.email=email;
    }
    public boolean isEqual(EmailAddress emailAddress){
        return (Objects.equals(email,emailAddress.getEmail()));
    }
}
