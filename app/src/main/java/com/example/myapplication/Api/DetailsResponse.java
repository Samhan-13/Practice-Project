package com.example.myapplication.Api;

import com.google.gson.annotations.SerializedName;

public class DetailsResponse {
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("email")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return email;
    }

    public void setAddress(String email) {
        this.email = this.email;
    }



}
