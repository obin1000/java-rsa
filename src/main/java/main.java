package main.java;

import rsa.java.Rsa;

class Main {

    public static void main(String[] args) {
       System.out.println("Welcome to the best RSA implementation in Java!");
//        GUI g = new GUI();
        Rsa calc = new Rsa();

        long duration = calc.calcPQ(997824234780887L);
        long p = calc.getP();
        long q = calc.getQ();

        System.out.println("p is " + p);
        System.out.println("q is " + q);
        System.out.println("It took: " + duration + " nanosec");
    }
}