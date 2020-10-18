package com.company;

import java.util.*;

public class Phone {
    private String name;
    private String surname;
    private Map<String,String> phones=new HashMap<String,String>();
Phone(String name,String surname,Map<String,String> phones){
    this.name=name;
    this.surname=surname;
   this.phones = phones;
}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Map<String, String> getPhones() {
        return phones;
    }

    public void setPhones(Map<String, String> phones) {
        this.phones = phones;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name+", "+surname+", "+phones;
    }





}
