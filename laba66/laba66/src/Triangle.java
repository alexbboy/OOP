public class Triangle implements Shape {
    private double sideA;
    private double sideB;
    private double sideC;
    public Triangle(double sideA,double sideB,double sideC){
        this.sideA=sideA;
        this.sideB=sideB;
        this.sideC=sideC;
        if((sideA<=0)||(sideB<=0)||(sideC<=0))
            throw new ArithmeticException("Wrong sides");
        if((sideA+sideB<=sideC) || (sideA+sideC<=sideB)||(sideB+sideC<=sideA)) {
            throw new RuntimeException("Wrong triangle");
        }
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public double calcArea(){
        double p=(sideA+sideB+sideC)/2;
        double area=Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC));
        return area;
    }
    public double calcPerimetr(){
        return sideA+sideB+sideC;
    }


    @Override
    public String toString() {
        return "Triangle " + "sideA=" + sideA +
                ", sideB=" + sideB +
                ", sideC=" + sideC +
                ' ';
    }
}
