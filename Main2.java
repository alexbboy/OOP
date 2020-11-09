package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	Phonebook book=new Phonebook();
	List<Number> list=new ArrayList<Number>();
	Number number1=new Number("89312280810","Home");
        Number number2=new Number("3568329","Mobile");
        list.add(0,number1);
        list.add(1,number2);
        book.AddPhone("Aleksey","Sleptsov",list);
        List<Number> list2=new ArrayList<Number>();
        Number number3=new Number("89116474185","Home");
        Number number4=new Number("3585047","Mobile");
        list2.add(0,number3);
        list2.add(1,number4);
        book.AddPhone("Danila","Ilyin",list2);
        List<Number> list3=new ArrayList<Number>();
        Number number5=new Number("675849582","Mobile");
        Number number6=new Number("3585047","Home");
        list3.add(0,number5);
        list3.add(1,number6);
        book.AddPhone("aleksender","Volkov",list3);
        List<Phone> test=book.find("89");
        for(int i=0;i<book.getList_of_phones().size();i++){
            System.out.println(book.getList_of_phones().get(i).getName()+" "+book.getList_of_phones().get(i).getSurname());
            for(int j=0;j<book.getList_of_phones().get(i).getPhones().size();j++)
                System.out.println(book.getList_of_phones().get(i).getPhones().get(j).getNumb()+" "+book.getList_of_phones().get(i).getPhones().get(j).getType());
            System.out.println("...................................................................");
        }
        System.out.println("Fonded numbers");
        for(int i=0;i<test.size();i++){
           System.out.println(test.get(i).getName()+" "+test.get(i).getSurname());
           for(int j=0;j<test.get(i).getPhones().size();j++)
               System.out.println(test.get(i).getPhones().get(j).getNumb()+" "+test.get(i).getPhones().get(j).getType());
        }
    }
}
