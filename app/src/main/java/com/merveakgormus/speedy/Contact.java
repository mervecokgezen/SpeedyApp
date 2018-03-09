package com.merveakgormus.speedy;

import android.view.View;

/**
 * Created by vestel on 08.03.2018.
 */

public class Contact {

    private String contact_name;
    private String contact_phone;
    private String contact_mail;


    public String getContact_name() {

        return contact_name;
    }

    public void setContact_name(String contact_name) {

        this.contact_name = contact_name;
    }

    public String getContact_phone() {

        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {

        this.contact_phone = contact_phone;
    }

    public String getContact_mail() {

        return contact_mail;
    }

    public void setContact_mail(String contact_mail) {

        this.contact_mail = contact_mail;
    }

    public Contact(String contact_name, String contact_phone, String contact_mail){
        this.contact_name  = contact_name;
        this.contact_phone = contact_phone;
        this.contact_mail  = contact_mail;


    }
    public Contact(){

    }

    public interface CustomItemClickListener {
        void onItemClick(View v, int position);
    }
}
