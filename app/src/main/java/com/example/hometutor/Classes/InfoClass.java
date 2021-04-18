package com.example.hometutor.Classes;

public class InfoClass{
    String firstName;
    String lastName;
    String graduated;
    String qualification;
    String phone;
    String address;
    String about;
    String  uri;

    public InfoClass(String firstName, String lastName, String graduated, String qualification, String phone, String address, String about, String uri) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.graduated = graduated;
        this.qualification = qualification;
        this.phone = phone;
        this.address = address;
        this.about = about;
        this.uri = uri;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGraduated() {
        return graduated;
    }

    public String getQualification() {
        return qualification;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAbout() {
        return about;
    }

    public String getUri() {
        return uri;
    }
}
