package com.example.sample.app.ui.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdatedUserDetailsRequestModel {
    @NotNull(message="First name cant be null")
    @Size(min=2, message = "First name must not be less than 2 characters")
    private String firstName;

    @NotNull(message="First name cant be null")
    @Size(min=2, message = "First name must not be less than 2 characters")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
