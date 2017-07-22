package ru.academits.erinary.main;

import ru.academits.erinary.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vectorA = new Vector(new double[]{1, 2, 3});
        Vector vectorB = new Vector(new double[]{1, 2, 3, 4, 5});
        Vector vectorC = new Vector(new double[]{1, 2, 3, 4, 5});

        System.out.printf("Вектор А: %s %n", vectorA.toString());
        System.out.printf("Его размерность: %d %n", vectorA.getSize());
        System.out.printf("Вектор B: %s %n", vectorB.toString());
        System.out.printf("Его размерность: %d %n", vectorB.getSize());

        System.out.printf("A + B = %s%n", new Vector(vectorA).addVector(vectorB));
        System.out.printf("B + A = %s%n", new Vector(vectorB).addVector(vectorA));
        System.out.printf("A - B = %s%n", new Vector(vectorA).subtractVector(vectorB));
        System.out.printf("B - A = %s%n", new Vector(vectorB).subtractVector(vectorA));

        double number = 2;
        System.out.printf("Умножение A на %.2f: %s%n", number, new Vector(vectorA).multiply(number));
        System.out.printf("Разворот вектора А: %s%n", new Vector(vectorA).invert());
        System.out.printf("Длина вектора А + В: %f%n", new Vector(vectorA).addVector(vectorB).getLength());

        if (vectorA.equals(vectorB)) {
            System.out.println("Векотра равны");
        } else {
            System.out.println("Вектора не равны");
        }
        System.out.println("Статичные методы:");
        System.out.printf("A + B: %s%n", Vector.add(vectorA, vectorB));
        System.out.printf("A - B: %s%n", Vector.subtract(vectorA, vectorB));
        System.out.printf("A * B: %.2f%n", Vector.multiply(vectorA, vectorB));
        vectorC.removeComponent(0);
        System.out.printf("Убрать компонент по индексу: %s", vectorC);
        }
    }
