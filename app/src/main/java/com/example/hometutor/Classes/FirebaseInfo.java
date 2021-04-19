package com.example.hometutor.Classes;

public class FirebaseInfo {
    private final String name;
    private final String age;
    private final String address;

    private final int Image;

    public FirebaseInfo(String name, String age, String address, int image) {
        this.name = name;
        this.age = age;
        this.address = address;
        Image = image;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public int getImage() {
        return Image;
    }
}
