package ru.academits.erinary.vector;


import java.util.Arrays;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Vector {
    private double[] components;

    public Vector(int dimension, double[] array) {
        this.components = new double[dimension];
        System.arraycopy(array, 0, this.components, 0, Math.min(dimension, array.length));
        for (int i = array.length; i < dimension; ++i) {
            this.components[i] = 0;
        }
    }

    public Vector(int dimension) {
        this.components = new double[dimension];
        for (int i = 0; i < dimension; ++i) {
            this.components[i] = 0;
        }
    }

    public Vector(Vector vector) {
        this.components = new double[vector.components.length];
        System.arraycopy(vector.components, 0, this.components, 0, vector.components.length);
    }

    public Vector(double[] array) {
        this.components = new double[array.length];
        System.arraycopy(array, 0, this.components, 0, array.length);
    }

    public int getSize() {
        return this.components.length;
    }

    public String toString() {
        return Arrays.toString(this.components);
    }

    public Vector addVector(Vector vectorA) {
        for (int i = 0; i < Math.min(this.components.length, vectorA.components.length); ++i) {
            this.components[i] = this.components[i] + vectorA.components[i];
        }
        return this;
    }

    public Vector subtractVector(Vector vectorA) {
        for (int i = 0; i < Math.min(this.components.length, vectorA.components.length); ++i) {
            this.components[i] = this.components[i] - vectorA.components[i];
        }
        return this;
    }

    public Vector multiply(double number) {
        for (int i = 0; i < this.components.length; ++i) {
            this.components[i] = this.components[i] * number;
        }
        return this;
    }

    public Vector invert() {
        return this.multiply(-1);
    }

    public double getLength() {
        double sum = 0;
        for (double component : this.components) {
            sum += Math.pow(component, 2);
        }
        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        return this.components[index];
    }

    public void setComponent(int index, double number) {
        this.components[index] = number;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;

        Vector vector = (Vector) object;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public static Vector add(Vector vectorA, Vector vectorB) {
        Vector result = new Vector(vectorA);
        return result.addVector(vectorB);
    }

    public static Vector subtract(Vector vectorA, Vector vectorB) {
        Vector result = new Vector(vectorA);
        return result.subtractVector(vectorB);
    }

    public static double multiply(Vector vectorA, Vector vectorB) {
        double result = 0;
        for (int i = 0; i < Math.min(vectorA.components.length, vectorB.components.length); ++i) {
            result += vectorA.components[i] * vectorB.components[i];
        }
        return result;
    }
}