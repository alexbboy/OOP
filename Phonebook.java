package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Phonebook {
    private List<Phone> List_of_phones=new ArrayList<Phone>();

    protected List<Phone> getList_of_phones() {
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
            if(equals(List_of_phones.get(i).getName(),name)&&equals(List_of_phones.get(i).getSurname(),surname))
                return List_of_phones.get(i);
        }
        return null;
    }
    public List<Phone> update(Phone old,String name,String surname,Map<String,String> phones){
        Phone newPhone=new Phone(name, surname, phones);
        for(int i=0;i<List_of_phones.size();i++){
            if(equals(List_of_phones.get(i).getName(),old.getName())&&equals(old.getSurname(), old.getSurname())){
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
            if(equals(List_of_phones.get(i).getName(),name)||equals(List_of_phones.get(i).getSurname(),surname))
                Delete=List_of_phones.remove(List_of_phones.get(i));
        }

        if(Delete==true){
            return List_of_phones;
        }
        throw new RuntimeException("Error! We can't find a number");
    }
    public String find(String text){
        String str="";
        str=str+"Found numbers"+";";
        for(int i=0;i<List_of_phones.size();i++){
            boolean check=false;
         for(Map.Entry<String,String> entry:List_of_phones.get(i).getPhones().entrySet()){
             if(equals(entry.getKey().contains(text),true)) {
                 str=str+List_of_phones.get(i).getName() + " " + List_of_phones.get(i).getSurname() + " " + List_of_phones.get(i).getPhones()+";";
                 break;
             }
         }
            if(equals(List_of_phones.get(i).getName().contains(text),true)||equals(List_of_phones.get(i).getSurname().contains(text),true))
            {
                str=str+List_of_phones.get(i).getName()+" "+List_of_phones.get(i).getSurname()+" "+List_of_phones.get(i).getPhones()+";";
            }
        }

        return str;
    }


    public boolean equals(Object a,Object b) {
        return Objects.equals(a,b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(List_of_phones);
    }
}
