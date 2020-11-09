package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Round implements Shape, Serializable{
        private double radius;
        public double getRadius(){
            return radius;
        }
        public Round(double radius){
            if(radius<=0)
                throw new ArithmeticException("Wrong radius");
            this.radius=radius;
        }
        public double calcArea(){
            return Math.PI*radius*radius;
        }
        public double calcPerimetr(){
            return 2*Math.PI*radius;
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

    @Override
        public String toString() {
            return "Round " +
                    "radius=" + radius +
                    ' ';
        }
    }


