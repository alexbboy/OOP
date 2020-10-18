package com.company;

public class Rectangle implements shape{
    private double width;
    private double hight;
    public Rectangle(double width,double hight){
        this.hight=hight;
        this.width=width;
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

}
