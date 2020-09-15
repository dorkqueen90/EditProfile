package com.example.editprofile;

import java.io.Serializable;

public class Student implements Serializable {
    String name;
    String email;
    String department;
    String mood;
    String element;
    int moodValue;

    public Student(String name, String email, String department, String mood, int moodValue, String element) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.mood = mood;
        this.moodValue = moodValue;
        this.element = element;
    }

    public void setElement(String element) {
        this.element = element;
    }
}
