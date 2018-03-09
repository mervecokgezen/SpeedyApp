package com.merveakgormus.speedy;

/**
 * Created by vestel on 07.03.2018.
 */

public class User {


    private String user_name_surname;
    private String user_email;
    private String user_phone;
    private String user_password;

    public User(String user_name_surname, String user_email, String user_phone, String user_password) {
        this.user_name_surname = user_name_surname;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.user_password = user_password;
    }

    public String getUser_name_surname() {

        return user_name_surname;
    }

    public void setUser_name_surname(String user_name_surname) {

        this.user_name_surname = user_name_surname;
    }

    public String getUser_email() {

        return user_email;
    }

    public void setUser_email(String user_email) {

        this.user_email = user_email;
    }

    public String getUser_phone() {

        return user_phone;
    }

    public void setUser_phone(String user_phone) {

        this.user_phone = user_phone;
    }

    public String getUser_password() {

        return user_password;
    }

    public void setUser_password(String user_password) {

        this.user_password = user_password;
    }




}
