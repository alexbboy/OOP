package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Phonebook {
    private List<Phone> List_of_phones=new ArrayList<Phone>();

    public List<Phone> getList_of_phones() {
        return List_of_phones;
    }

    public void setList_of_phones(List<Phone> list_of_phones) {
        List_of_phones = list_of_phones;
    }
    public List<Phone> AddPhone(String name, String surname, Map<String,String> phones){
        Phone contact=new Phone(name, surname, phones);
        List_of_phones.add(contact);
        return List_of_phones;
    }
    public Phone GetPhone(String name,String surname){
        for(int i=0;i<List_of_phones.size();i++){
            if((List_of_phones.get(i).getName()==name)&&(List_of_phones.get(i).getSurname()==surname))
                return List_of_phones.get(i);
        }
        return null;
    }
    public List<Phone> update(Phone old,String name,String surname,Map<String,String> phones){
        Phone newPhone=new Phone(name, surname, phones);
        for(int i=0;i<List_of_phones.size();i++){
            if((List_of_phones.get(i).getName()==old.getName())&&(old.getSurname()== old.getSurname())){
                List_of_phones.set(i,newPhone);
                return List_of_phones;
            }
        }
        throw new RuntimeException("Error! We can't find a number");
    }
    public List<Phone> delete(String name,String surname){
        int i=0;
        boolean Delete=false;
        for(i=0;i<List_of_phones.size();i++){
            if((List_of_phones.get(i).getName()==name)||(List_of_phones.get(i).getSurname()==surname))
                Delete=List_of_phones.remove(List_of_phones.get(i));
        }

        if(Delete==true){
            return List_of_phones;
        }
        throw new RuntimeException("Error! We can't find a number");
    }
    public void find(String text){
        System.out.println("Found numbers");
        for(int i=0;i<List_of_phones.size();i++){
            boolean check=false;
         for(Map.Entry<String,String> entry:List_of_phones.get(i).getPhones().entrySet()){
             if(entry.getKey().contains(text)==true) {
                 System.out.println(List_of_phones.get(i).getName() + " " + List_of_phones.get(i).getSurname() + " " + List_of_phones.get(i).getPhones());
                 break;
             }
         }
            if((List_of_phones.get(i).getName().contains(text)==true)||(List_of_phones.get(i).getSurname().contains(text)==true))
            {
                System.out.println(List_of_phones.get(i).getName()+" "+List_of_phones.get(i).getSurname()+" "+List_of_phones.get(i).getPhones());
            }
        }
        System.out.println(".....................................................................................................................................");
    }
}
