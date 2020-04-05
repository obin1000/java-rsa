package rsa.java;

import java.math.BigInteger;

public class Rsa {

    private long p;
    private long q;
    private long e;

    public long getP() {
        return p;
    }

    public long getQ() {
        return q;
    }

    public long getE() {
        return e;
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
            //System.out.println("checked p = " + p);
        }
        return 0;
    }

    public void calcE(long startAt) {
        long multiple = (this.p - 1) * (this.q - 1);

        while (gcd(multiple, startAt) != 1) {
            startAt++;
        }
        this.e = startAt;
    }

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