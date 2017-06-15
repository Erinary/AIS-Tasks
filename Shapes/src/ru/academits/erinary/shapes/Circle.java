package ru.academits.erinary.shapes;

public class Circle implements Shape {
    private double radii;

    public Circle(double radii) {
        this.radii = radii;
    }

    @Override
    public double getWidth() {
        return 2 * radii;
    }

    @Override
    public double getHeight() {
        return 2 * radii;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radii, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radii;
    }
}
