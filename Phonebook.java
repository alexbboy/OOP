package com.company;

import java.util.*;

public final class Phonebook {
    private List<Phone> listOfContacts=new ArrayList<Phone>();

    public List<Phone> getList_of_phones() {
        return Collections.unmodifiableList(listOfContacts);
    }

    public List<Phone> AddPhone(String name, String surname, List<Number> phones){
        Phone contact=new Phone(name, surname, phones);
        listOfContacts.add(contact);
        return Collections.unmodifiableList(listOfContacts);
    }
    public Phone GetPhone(String name,String surname){
        for(int i=0;i<listOfContacts.size();i++){
            if((listOfContacts.get(i).getName().equals(name))&&(listOfContacts.get(i).getSurname().equals(surname)))
                return listOfContacts.get(i);
        }
        return null;
    }
    public List<Phone> update(Phone old,String name,String surname,List<Number> phones){
        Phone newPhone=new Phone(name, surname, phones);
        for(int i=0;i<listOfContacts.size();i++){
            if((listOfContacts.get(i).getName().equals(old.getName()))&&(old.getSurname().equals(old.getSurname()))){
                listOfContacts.set(i,newPhone);
                return Collections.unmodifiableList(listOfContacts);
            }
        }
        throw new RuntimeException("Error! We can't find a number");
    }
    public List<Phone> delete(String name,String surname){
        int i=0;
        boolean Delete=false;
        for(i=0;i<listOfContacts.size();i++){
            if((listOfContacts.get(i).getName().equals(name))||(listOfContacts.get(i).getSurname().equals(surname)))
                Delete=listOfContacts.remove(listOfContacts.get(i));
        }

        if(Delete==true){
            return listOfContacts;
        }
        throw new RuntimeException("Error! We can't find a number");
    }
    public List<Phone> find(String text){
        int j=0;
        List<Phone> newList=new ArrayList<Phone>();
        for(int i=0;i<listOfContacts.size();i++){
             if((listOfContacts.get(i).getName().contains(text)==true)||(listOfContacts.get(i).getSurname().contains(text)==true)) {
                 Phone phone=new Phone(listOfContacts.get(i).getName(),listOfContacts.get(i).getSurname(),listOfContacts.get(i).getPhones());
                 newList.add(phone);
                 break;
             }
for(int k=0;k<listOfContacts.get(i).getPhones().size();k++){
    if(listOfContacts.get(i).getPhones().get(k).getNumb().contains(text)==true){
        Phone phone=new Phone(listOfContacts.get(i).getName(),listOfContacts.get(i).getSurname(),listOfContacts.get(i).getPhones());
        newList.add(phone);
        break;
    }

}

        }

        return newList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phonebook)) return false;
        Phonebook phonebook = (Phonebook) o;
        return listOfContacts.equals(phonebook.listOfContacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfContacts);
    }
}
