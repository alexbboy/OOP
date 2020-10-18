package com.company;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List Rounds=new ArrayList<Round>();
        List Triangles =new ArrayList<Triangle>();
        List Rectangles=new ArrayList<Rectangle>();
        List Squares=new ArrayList<Square>();
        Round round1=new Round(10);
        Round round2=new Round(7);
        Round round3=new Round(12);
        Rounds.add(round1);
        Rounds.add(round2);
        Rounds.add(round3);
        Square square1=new Square(10);
        Square square2=new Square(7);
        Square square3=new Square(12);
        Squares.add(square1);
        Squares.add(square2);
        Squares.add(square3);
        Triangle triangle1=new Triangle(10,8,6);
        Triangle triangle2=new Triangle(13,9,7);
        Triangle triangle3=new Triangle(6,8,9);
        Triangles.add(triangle1);
        Triangles.add(triangle2);
        Triangles.add(triangle3);
        Rectangle rectangle1=new Rectangle(10,5);
        Rectangle rectangle2=new Rectangle(6,8);
        Rectangle rectangle3=new Rectangle(12,9);
        Rectangles.add(rectangle1);
        Rectangles.add(rectangle2);
        Rectangles.add(rectangle3);
        Main object=new Main();
        double a=object.calcTrianglesArea(Triangles);
        double b=object.calcRectanglesArea(Rectangles);
        double c=object.calcRoundsArea(Rounds);
        double d=object.calcSquaresArea(Squares);
        double sum=a+b+c+d;
        System.out.println("Sum of Triangles= "+a+";"+"Sum of Rectangles= "+b+";"+"Sum of Circles= "+c+";"+" Sum of squares= "+d);
        System.out.println("Sum of all= "+sum);
object.max_Triangle(Triangles);
object.max_Rectangle(Rectangles);
object.max_Square(Squares);
object.max_Circle(Rounds);
    }
public void max_Triangle(List<Triangle> object){
        double min=Double.MAX_VALUE;
        double max=0;
        double minPer=Double.MAX_VALUE;
        double maxPer=0;
        int MaxPerimetr=0;
        int MinPerimetr=0;
        int index_max=0;
        int index_min=0;
        for(int i=0;i<object.size();i++){
            if(object.get(i).calcPerimetr()>maxPer){
                maxPer=object.get(i).calcPerimetr();
                MaxPerimetr=i;
            }
            if(object.get(i).calcPerimetr()<minPer){
                minPer=object.get(i).calcPerimetr();
                MinPerimetr=i;
            }
            if(object.get(i).calcArea()>max){
                max=object.get(i).calcArea();
                index_max=i;
            }
            if(object.get(i).calcArea()<min){
                min=object.get(i).calcArea();
                index_min=i;
            }
        }
    System.out.println("Maximum Triangle Area is "+max+";"+"side a= "+object.get(index_max).getSideA()+";"+"side b="+object.get(index_max).getSideB()+";"+"side c="+object.get(index_max).getSideC());
    System.out.println("Minimum Triangle Area is "+min+";"+"side a= "+object.get(index_min).getSideA()+";"+"side b="+object.get(index_min).getSideB()+";"+"side c="+object.get(index_min).getSideC());
    System.out.println("Maximum Triangle Perimetr is "+maxPer+";"+"side a= "+object.get(MaxPerimetr).getSideA()+";"+"side b="+object.get(MaxPerimetr).getSideB()+";"+"side c="+object.get(MaxPerimetr).getSideC());
    System.out.println("Minimum Triangle Perimetr is "+minPer+";"+"side a= "+object.get(MinPerimetr).getSideA()+";"+"side b="+object.get(MinPerimetr).getSideB()+";"+"side c="+object.get(MinPerimetr).getSideC());
}
    public void max_Rectangle(List<Rectangle> object){
        double min=Double.MAX_VALUE;
        double max=0;
        double minPer=Double.MAX_VALUE;
        double maxPer=0;
        int MaxPerimetr=0;
        int MinPerimetr=0;
        int index_max=0;
        int index_min=0;
        for(int i=0;i<object.size();i++){
            if(object.get(i).calcPerimetr()>maxPer){
                maxPer=object.get(i).calcPerimetr();
                MaxPerimetr=i;
            }
            if(object.get(i).calcPerimetr()<minPer){
                minPer=object.get(i).calcPerimetr();
                MinPerimetr=i;
            }
            if(object.get(i).calcArea()>max){
                max=object.get(i).calcArea();
                index_max=i;
            }
            if(object.get(i).calcArea()<min){
                min=object.get(i).calcArea();
                index_min=i;
            }
        }
        System.out.println("Maximum Rectangle Area is "+max+";"+"Width= "+object.get(index_max).getWidth()+";"+"Hight="+object.get(index_max).getHight());
        System.out.println("Minimum Rectangle Area is "+min+";"+"Width= "+object.get(index_min).getWidth()+";"+"Hight="+object.get(index_min).getHight());
        System.out.println("Maximum Rectangle Perimetr is "+maxPer+";"+"Width= "+object.get(MaxPerimetr).getWidth()+";"+"Hight="+object.get(MaxPerimetr).getHight());
        System.out.println("Minimum Rectangle Perimetr is "+minPer+";"+"Width= "+object.get(MinPerimetr).getHight()+";"+"Hight="+object.get(MinPerimetr).getHight());
    }
    public void max_Circle(List<Round> object){
        double min=Double.MAX_VALUE;
        double max=0;
        double minPer=Double.MAX_VALUE;
        double maxPer=0;
        int max_index1=0;
        int max_index2=0;
        int min_index1=0;
        int min_index2=0;
        for(int i=0;i<object.size();i++){
            if(object.get(i).calcPerimetr()>maxPer){
                maxPer=object.get(i).calcPerimetr();
                max_index1=i;
            }
            if(object.get(i).calcPerimetr()<minPer){
                minPer=object.get(i).calcPerimetr();
                min_index1=1;
            }
            if(object.get(i).calcArea()>max){
                max=object.get(i).calcArea();
                max_index2=i;
            }
            if(object.get(i).calcArea()<min){
                min=object.get(i).calcArea();
                min_index2=i;
            }
        }
        System.out.println("Maximum Circle Area is "+max+";"+"Radius= "+object.get(max_index1).getRadius());
        System.out.println("Minimum Circle Area is "+min+";"+"Radius= "+object.get(min_index1).getRadius());
        System.out.println("Maximum Circle Perimetr is "+maxPer+";"+"Radius="+"= "+object.get(max_index2).getRadius());
        System.out.println("Minimum Circle Perimetr is "+minPer+";"+"Side= "+object.get(min_index2).getRadius());
    }
    public void max_Square(List<Square> object){
        double min=Double.MAX_VALUE;
        double max=0;
        double minPer=Double.MAX_VALUE;
        double maxPer=0;
        for(int i=0;i<object.size();i++){
            if(object.get(i).calcPerimetr()>maxPer){
                maxPer=object.get(i).calcPerimetr();
            }
            if(object.get(i).calcPerimetr()<minPer){
                minPer=object.get(i).calcPerimetr();
            }
            if(object.get(i).calcArea()>max){
                max=object.get(i).calcArea();
            }
            if(object.get(i).calcArea()<min){
                min=object.get(i).calcArea();
            }
        }
        System.out.println("Maximum Square Area is "+max+";"+"Side= "+Math.sqrt(max));
        System.out.println("Minimum Square Area is "+min+";"+"Side= "+Math.sqrt(min));
        System.out.println("Maximum Squares Perimetr is "+maxPer+";"+"Side= "+maxPer/4);
        System.out.println("Minimum Squares Perimetr is "+minPer+";"+"Side= "+minPer/4);
    }

    public double calcTrianglesArea(List<Triangle> examps){
        double sum=0;
        for(int i=0;i<examps.size();i++){
            double sideA=examps.get(i).getSideA();
            double sideB=examps.get(i).getSideB();
            double sideC=examps.get(i).getSideC();
            sum=sum+examps.get(i).calcArea();
        }
return sum;
    }
    public double calcRoundsArea(List<Round> examps){
        double sum=0;
        for(int i=0;i<examps.size();i++){
            sum=sum+examps.get(i).calcArea();
        }
        return sum;
    }
    public double calcSquaresArea(List<Square> examps){
        double sum=0;
        for(int i=0;i<examps.size();i++){
            sum=sum+examps.get(i).calcArea();
        }
        return sum;
    }
    public double calcRectanglesArea(List<Rectangle> examps){
        double sum=0;
        for(int i=0;i<examps.size();i++){
            double width=examps.get(i).getWidth();
            double hight=examps.get(i).getHight();
            sum=sum+examps.get(i).calcArea();
        }
        return sum;
    }
}
