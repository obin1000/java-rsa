package rsa.java;

import java.math.BigInteger;

public class Rsa {

    private static long p;
    private static long q;

    public long getP() {
        return p;
    }

    public long getQ() {
        return q;
    }

    public long calcPQ(long n) {
        long startTime = System.nanoTime();

        long biggestQ = (long) Math.pow(n, 2);
        long p = 2;

        while (p < biggestQ) {

            if (n % p == 0) {

                long q = n / p;

                if (checkPrime(q)) {
                    Rsa.q = q;
                    Rsa.p = p;

                    long endTime = System.nanoTime();
                    return (endTime - startTime);
                }
            }
            p = nextPrime(p);
            //System.out.println("checked p = " + p);
        }
        return 0;
    }

    // Function to get nextPrimeNumber
    static long nextPrime(long n) {
        BigInteger b = new BigInteger(String.valueOf(n));
        return Long.parseLong(b.nextProbablePrime().toString());
    }

    //Function to check and return prime numbers
    static boolean checkPrime(long n) {
        // Converting long to BigInteger
        BigInteger b = new BigInteger(String.valueOf(n));

        return b.isProbablePrime(1);
    }
}