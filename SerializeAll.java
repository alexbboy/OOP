package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeAll {
    private List<Shape> list=new ArrayList<>();


    public void save(List<Shape> list) {
        try (FileOutputStream file = new FileOutputStream("C:\\Users\\Alex\\Desktop\\Save.json");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(file)){

            objectOutputStream.writeObject(list);


        }
        catch(NullPointerException d){
            throw new NullPointerException("File is empty");
        }
        catch(FileNotFoundException d)
        {
            throw new RuntimeException("File dont found");

        }
        catch(IOException d){
            throw new RuntimeException("Exception");
        }


    }

    public List<Shape> load(){
        List<Shape> newlist = new ArrayList<>();
        try(FileInputStream file = new FileInputStream("C:\\Users\\Alex\\Desktop\\Save.json");
            ObjectInputStream objectOutputStream = new ObjectInputStream(file);) {
            newlist=(List<Shape>) objectOutputStream.readObject();
        }
        catch(NullPointerException d){
            throw new NullPointerException("File is empty");
        }
        catch(FileNotFoundException d)
        {
            throw new RuntimeException("File dont found");

        }
        catch(IOException d){
            throw new RuntimeException("Exception");
        }
        catch (ClassNotFoundException d){
            throw new RuntimeException("Class not found");
        }

        return newlist;
    }
}
