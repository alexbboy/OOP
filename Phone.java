package com.company;

import java.util.*;

public final class Phone {
    private String name;
    private String surname;
List<Number> phones=new ArrayList();
Phone(String name,String surname,List<Number> phones){
    this.name=new String();
    this.name=name;
    this.surname=new String();
    this.surname=surname;
    this.phones=new ArrayList<>();
    for(Number number:phones)
   this.phones.add(number);
}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    public List<Number> getPhones() {
        return phones;
    }



    @Override
    public String toString() {
        return name+", "+surname+", "+phones;
    }





}
