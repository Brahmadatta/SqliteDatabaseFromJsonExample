package com.example.sccm.sqlitedbfromjsonexample;

public class CountryCodeModel {

    public String country_name;

    public String dial_code;


    public CountryCodeModel(String country_name, String dial_code) {
        this.country_name = country_name;
        this.dial_code = dial_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getDial_code() {
        return dial_code;
    }

    public void setDial_code(String dial_code) {
        this.dial_code = dial_code;
    }
}
