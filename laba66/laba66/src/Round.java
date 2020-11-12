public class Round implements Shape {
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



    @Override
    public String toString() {
        return "Round " +
                "radius=" + radius +
                ' ';
    }
}
