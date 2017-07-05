package ru.academits.erinary.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public double getSideLength (double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private double getSideA(){
        return this.getSideLength(x1, y1, x2, y2);
    }

    private double getSideB(){
        return this.getSideLength(x2, y2, x3, y3);
    }

    private double getSideC(){
        return this.getSideLength(x3, y3, x1, y1);
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        double p = this.getPerimeter() / 2;
        return Math.sqrt(p * (p - this.getSideA()) * (p - this.getSideB()) * (p - this.getSideC()));
    }

    @Override
    public double getPerimeter() {
        return this.getSideA() + this.getSideB() + this.getSideC();
    }

    @Override
    public String toString() {
        return String.format("Треугольник A(%.2f, %.2f) B(%.2f, %.2f) C(%.2f, %.2f)", x1, y1, x2, y2, x3, y3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        if (Double.compare(triangle.x1, x1) != 0) {
            return false;
        }
        if (Double.compare(triangle.y1, y1) != 0) {
            return false;
        }
        if (Double.compare(triangle.x2, x2) != 0) {
            return false;
        }
        if (Double.compare(triangle.y2, y2) != 0) {
            return false;
        }
        if (Double.compare(triangle.x3, x3) != 0) {
            return false;
        }
        return Double.compare(triangle.y3, y3) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x1);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x3);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y3);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
