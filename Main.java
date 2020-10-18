package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	Phonebook book=new Phonebook();
        Map<String,String> number=new HashMap<String,String>();
        number.put("89312280810","mobile");
        number.put("3881622","home");
	book.AddPhone("Aleksey","Sleptsov",number);
        Map<String,String> number1=new HashMap<String,String>();
        number1.put("89054317583","mobile");
        number1.put("3703455","home");
        book.AddPhone("Aleksander","Kuznetsnov",number1);
        Map<String,String> number2=new HashMap<String,String>();
        number2.put("899912315432","mobile");
        book.AddPhone("Aleksandra","Kuznetsova",number2);
        Map<String,String> number3=new HashMap<String,String>();
        number3.put("89014357856","mobile");
        number3.put("3156734","home");
        number3.put("3557190","work");
        book.AddPhone("Danila","Ilyin",number3);
        Map<String,String> number4=new HashMap<String,String>();
        number4.put("890556893","mobile");
        number4.put("3358877","home");
        number4.put("30245640","work");
        book.AddPhone("Dekanat","Kolpakov",number4);
        book.find("31");
        book.find("Da");
        book.delete("Aleksandra","Kuznetsova");
        Map<String,String> number5=new HashMap<String,String>();
        number5.put("89055689378","mobile");
        number5.put("3884554","home");
        number5.put("3000505","work");
        number5.put("7348646","mobile");
        book.update(book.GetPhone("Dekanat","Kolpakov"),"Dekan","Mironov",number5);
        for(int i=0;i<book.getList_of_phones().size();i++)
            System.out.println(book.getList_of_phones().get(i).getName()+" "+book.getList_of_phones().get(i).getSurname()+" "+book.getList_of_phones().get(i).getPhones());
    }
}
