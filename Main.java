package com.company;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Shape> shapes = new ArrayList<>();
        double sum = 0;
        shapes.add(new Round(7));
        shapes.add(new Round(5));
        shapes.add(new Triangle(3, 6, 8));
        shapes.add(new Triangle(2,3,4));
        shapes.add(new Rectangle(10, 5));

        shapes.add(new Square(12));
        for (int i = 0; i < shapes.size(); i++) {
            sum = sum + shapes.get(i).calcArea();
        }
        System.out.println("Sum= " + sum);
        String filename="C:\\Users\\Alex\\Desktop\\Save.ser";
SerializeAll Save=new SerializeAll(filename);

List<Shape> shapes1=new ArrayList<>();
try {
    Save.save(shapes);
}
catch (FileNotFoundException e ){
    throw  new RuntimeException("File not found",e);
}
catch (IOException d){
    throw new RuntimeException("Failed",d);
}
try {
    shapes1 = Save.load();
}
catch (FileNotFoundException e){
    throw new RuntimeException("File not found",e);
}
catch (ClassNotFoundException d){
    throw new RuntimeException("Exception",d);
}
catch (IOException q){
    throw new RuntimeException("Failed",q);
}
for(int i=0;i<shapes1.size();i++){
    System.out.println(shapes1.get(i).toString());
}

}



    public void max(List<Shape> object) {
        int maximum = 0;
        int minimum = 0;
        int maximumPer = 0;
        int minimumPer = 0;
        double maxPer = 0;
        double minPer = Double.MAX_VALUE;
        double maxim = 0;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < object.size(); i++) {
            if (object.get(i).calcArea() > maxim) {
                maxim = object.get(i).calcArea();
                maximum = i;
            }
            if (object.get(i).calcArea() < min) {
                min = object.get(i).calcArea();
                minimum = i;
            }
            if (object.get(i).calcPerimetr() > maxPer) {
                maxPer = object.get(i).calcPerimetr();
                maximumPer = i;
            }
            if (object.get(i).calcPerimetr() < minPer) {
                minPer = object.get(i).calcPerimetr();
                minimumPer = i;
            }
        }
        System.out.println("Maximum area= " + object.get(maximum).toString());
        System.out.println("Minimum area= " + object.get(minimum).toString());
        System.out.println("Maximum perimetr= " + object.get(maximumPer).toString());
        System.out.println("Minimum perimetr= " + object.get(minimumPer).toString());
    }





}