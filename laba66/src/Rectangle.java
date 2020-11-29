public class Rectangle implements Shape {
    private double width;
    private double hight;
    public Rectangle(double width,double hight){
        if((width<=0)||(hight<=0))
            throw new ArithmeticException("Wrong side");
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

    @Override
    public String toString() {
        return "Rectangle " +
                "width=" + width +
                ", hight=" + hight +
                ' ';
    }
}
