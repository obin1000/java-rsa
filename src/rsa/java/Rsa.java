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

    public void calcPQ(long n) {
        long biggestQ = (long) Math.pow(n, 2);
        long p = 2;

        while (p < biggestQ) {

            if (n % p == 0) {

                long q = n / p;

                if (checkPrime(q)) {
                    Rsa.q = q;
                    Rsa.p = p;
                }
                return;
            }
            p = nextPrime(p);
        }
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