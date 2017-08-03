package ru.academits.erinary.test;


public class Main {
    public static void main(String[] args) {
        Thread counter = new Thread(new Counter());
        counter.start();
        try {
            counter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Исполнение продолжено");
    }
}
