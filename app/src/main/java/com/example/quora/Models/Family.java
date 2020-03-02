package com.example.quora.Models;

public class Family {
    private String uid;
    private String firstname;
    private String lastname;
    private String gender;
    private String contact;
    private String email;
    private String relation;
    private String age;

    public Family() {
    }

    public Family(String firstname) {
        this.firstname = firstname;
    }

    public Family(String uid, String firstname, String lastname, String gender, String contact, String email, String relation, String age) {
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.relation = relation;
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGender() {
        return gender;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getRelation() {
        return relation;
    }

    public String getAge() {
        return age;
    }
}
