package com.teso.AnaTransferserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



public class UpdateProfile {
//    private String email;
    private String address;
    private int mobiphone;
    private String userCountry;

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMobiphone() {
        return mobiphone;
    }

    public void setMobiphone(int mobiphone) {
        this.mobiphone = mobiphone;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }
}
