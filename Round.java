package com.company;


    public class Round implements shape{
        private double radius;
        public double getRadius(){
            return radius;
        }
        public void setRadius(double radius){
            this.radius=radius;
        }
        public Round(double radius){
            this.radius=radius;
        }
        public double calcArea(){
            return Math.PI*radius*radius;
        }
        public double calcPerimetr(){
            return 2*Math.PI*radius;
        }
    }


