package main.java;

import main.java.rsa.Rsa;

import java.math.BigInteger;

public class SpeedTest {

    private static int numOfMeasurements = 5;
    private static int testIncreaseSpeed = 3;

    public static void main(String[] args) {
        System.out.println("I am speed!");
        long time;
        Rsa rsa = new Rsa();
        BigInteger N ;
        BigInteger Next =  BigInteger.valueOf(2);
        StringBuilder row;

        while (true) {
            row = new StringBuilder();
            Next = Next.multiply(BigInteger.valueOf(testIncreaseSpeed));
            Next = Next.nextProbablePrime();
            N = Next.multiply(Next.nextProbablePrime());
            for (int i = 0; i < numOfMeasurements; i++) {
                time = rsa.calcPQ(N);
                row.append("\t");
                row.append(time);
            }
            System.out.print(row);
            System.out.print("\t\t");
            System.out.println(N);
        }
    }
}
