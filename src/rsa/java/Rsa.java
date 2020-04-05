package rsa.java;

import java.math.BigInteger;

public class Rsa {

    private long p;
    private long q;
    private long e;
    private long d;

    public long getP() {
        return p;
    }

    public long getQ() {
        return q;
    }

    public long getE() {
        return e;
    }

    public long getD() {
        return d;
    }


    public long calcPQ(long n) {
        long startTime = System.nanoTime();

        long biggestQ = (long) Math.pow(n, 2);
        long p = 2;

        while (p < biggestQ) {

            if (n % p == 0) {

                long q = n / p;

                if (checkPrime(q)) {
                    this.q = q;
                    this.p = p;

                    long endTime = System.nanoTime();
                    return (endTime - startTime);
                }
            }
            p = nextPrime(p);
        }
        return 0;
    }

    public void calcE(long startAt) {
        long multiple = (this.p - 1) * (this.q - 1);

        if (startAt > multiple){
            startAt = 2;
        }

        while (gcd(multiple, startAt) != 1) {
            startAt++;
        }
        if (startAt > multiple){
            calcE(startAt);
        } else{
            this.e = startAt;
        }

    }

    public void calcD(long n, long e){
        calcPQ(n);
        long multiple = (this.p - 1) * (this.q - 1);

        for (long d = 0; d < multiple; d++) {
            long comparator = d * e;
            if (comparator % multiple == 1){
                this.d = d;
                return;
            }
        }
    }

//    public String encrypt(String toEncrypt){
//        String encrypted = null;
//
//        for(int i = 0; i < toEncrypt.length(); i++)
//        {
//            char letter = toEncrypt.charAt(i);
//            int alphabeticNumber = letterToNumber(letter);
//
//        }
//    return encrypted;
//    }
//
//    private static int letterToNumber(char c) {
//        return  c^64;
//    }

    private static long gcd(long a, long b) {
        BigInteger b1 = new BigInteger(String.valueOf(a));
        BigInteger b2 = new BigInteger(String.valueOf(b));
        return Long.parseLong(b1.gcd(b2).toString());
    }

    // Function to get nextPrimeNumber
    private static long nextPrime(long n) {
        BigInteger b = new BigInteger(String.valueOf(n));
        return Long.parseLong(b.nextProbablePrime().toString());
    }

    //Function to check and return prime numbers
    private static boolean checkPrime(long n) {
        // Converting long to BigInteger
        BigInteger b = new BigInteger(String.valueOf(n));

        return b.isProbablePrime(1);
    }
}