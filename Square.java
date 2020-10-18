package com.company;

public class Square implements shape {
    private double x;

    private double getX(){
        return x;
    }
    private void setX(double X){
        this.x=x;
    }
    public Square(double x){

        this.x=x;
    }

        public double calcArea() {
            return x * x;
        }

        public double calcPerimetr() {
            return 4 * x;
        }


}