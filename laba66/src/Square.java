public class Square implements Shape {
    private double x;

    private double getX() {
        return x;
    }

    public Square(double x) {
        if (x <= 0)
            throw new ArithmeticException("Wrong side");
        this.x = x;
    }


    public double calcArea() {
        return x * x;
    }

    public double calcPerimetr() {
        return 4 * x;
    }

    @Override
    public String toString() {
        return "Square " + "x=" + x +
                ' ';
    }
}
