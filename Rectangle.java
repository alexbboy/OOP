package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Rectangle implements Shape, Serializable {
    private double width;
    private double hight;
    public Rectangle(double width,double hight){
        if((width<=0)||(hight<=0))
            throw new ArithmeticException("Wrong side");
        this.hight=hight;
        this.width=width;
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


    public double getHight() {
        return hight;
    }

    public double getWidth() {
        return width;
    }

    public void setHight(double hight) {
        this.hight = hight;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    public double calcArea(){
        return width*hight;
    }
    public double calcPerimetr(){
        return (width+hight)*2;
    }

    @Override
    public String toString() {
        return "Rectangle " +
                "width=" + width +
                ", hight=" + hight +
                ' ';
    }
}
