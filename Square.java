package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Square implements Shape, Serializable {
    private double x;

    private double getX(){
        return x;
    }
    public Square(double x){
if(x<=0)
    throw new ArithmeticException("Wrong side");
        this.x=x;
    }
    public void save(List<Shape> list) {
        try {
            FileOutputStream file = new FileOutputStream("C:\\Users\\Alex\\Desktop\\Save.json");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
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
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\Alex\\Desktop\\Save.json");
            ObjectInputStream objectOutputStream = new ObjectInputStream(file);
            newlist=(List<Shape>) objectOutputStream.readObject();
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


        public double calcArea() {
            return x * x;
        }

        public double calcPerimetr() {
            return 4 * x;
        }

    @Override
    public String toString() {
        return "Square " + "x=" + x +
                ' ';
    }
}