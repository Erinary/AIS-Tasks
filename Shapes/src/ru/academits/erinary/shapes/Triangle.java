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

    private static double getSideLength (double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private double getSideA(){
        return Triangle.getSideLength(x1, y1, x2, y2);
    }

    private double getSideB(){
        return Triangle.getSideLength(x2, y2, x3, y3);
    }

    private double getSideC(){
        return Triangle.getSideLength(x3, y3, x1, y1);
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
        return this.x1 == ((Triangle) o).x1 && this.x2 == ((Triangle) o).x2 && this.x3 == ((Triangle) o).x3
                && this.y1 == ((Triangle) o).y1 && this.y2 == ((Triangle) o).y2 && this.y3 == ((Triangle) o).y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + (int)x1;
        hash = prime * hash + (int)y1;
        hash = prime * hash + (int)x2;
        hash = prime * hash + (int)y2;
        hash = prime * hash + (int)x3;
        hash = prime * hash + (int)y3;
        return hash;
    }
}
