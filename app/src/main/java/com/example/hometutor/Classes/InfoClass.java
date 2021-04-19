package com.example.hometutor.Classes;

import android.net.Uri;

public class InfoClass {
    private final String name;
    private final String age;
    private final String graduate;
    private final String education;
    private final String phone;
    private final String address;
    private final String about;

    private final String imageUri;

    public InfoClass(String name, String age, String graduate, String education,
                     String phone, String address, String about, String imageUri) {
        this.name = name;
        this.age = age;
        this.graduate = graduate;
        this.education = education;
        this.phone = phone;
        this.address = address;
        this.about = about;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGraduate() {
        return graduate;
    }

    public String getEducation() {
        return education;
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

    public String getImageUri() {
        return imageUri;
    }
}
