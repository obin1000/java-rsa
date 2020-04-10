package main.java.rsa;

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
        return calcPQ(BigInteger.valueOf(n));
    }


    public long calcPQ(BigInteger n) {
        BigInteger q, p = BigInteger.valueOf(2);
        long startTime = System.currentTimeMillis();

        while (p.compareTo(n) < 0) {
            if (n.mod(p).getLowestSetBit() == -1) {

                q = n.divide(p);

                if (q.isProbablePrime(1)) {
                    this.q = q.longValue();
                    this.p = p.longValue();

                    long endTime = System.currentTimeMillis();
                    return (endTime - startTime);
                }
            }
            p = p.nextProbablePrime();
        }
        return 0;
    }

    public void calcE(long startAt) {
        long multiple = (this.p - 1) * (this.q - 1);

        if (startAt > multiple) {
            startAt = 2;
        }

        while (gcd(multiple, startAt) != 1) {
            startAt++;
        }
        if (startAt > multiple) {
            calcE(startAt);
        } else {
            this.e = startAt;
        }

    }

    public boolean validateE(long n, long e) {
        calcPQ(n);
        long multiple = (this.p - 1) * (this.q - 1);
        return gcd(multiple, e) == 1;
    }

    public void calcD(long n, long e) {
        BigInteger bigE = new BigInteger(String.valueOf(e));
        BigInteger multiple = new BigInteger(String.valueOf((this.p - 1) * (this.q - 1)));

        this.d = Long.parseLong(bigE.modInverse(multiple).toString());

//        for (long d = 0; d < multiple; d++) {
//            System.out.println(d);
//            long comparator = d * e;
//            if (comparator % multiple == 1) {
//                this.d = d;
//                return;
//            }
//        }
    }

    public String decrypt(String toDecrypt, long n) {
        toDecrypt = toDecrypt.replace(" ", "");
        StringBuilder str = new StringBuilder("");

        String[] codedLetters = toDecrypt.split(",");

        for (String letter : codedLetters) {
            BigInteger e = new BigInteger(letter);
            BigInteger d = new BigInteger(String.valueOf(this.d));
            BigInteger bigN = new BigInteger(String.valueOf(n));
            BigInteger c = e.modPow(d, bigN);

            int character = Integer.parseInt(String.valueOf(c));
            str.append((char) (character));
        }

        return str.toString();
    }

    public String encrypt(String toEncrypt) {
        StringBuilder str = new StringBuilder("");
        BigInteger n = new BigInteger(String.valueOf(this.p * this.q));

        for (int i = 0; i < toEncrypt.length(); i++) {
            int letterValue = (int) (toEncrypt.charAt(i));
            BigInteger value = new BigInteger(String.valueOf(letterValue));
            BigInteger e = new BigInteger(String.valueOf(this.e));
            BigInteger c = value.modPow(e, n);
            str.append(c);
            str.append(",");

        }
        str.deleteCharAt( str.length() - 1 );
        return str.toString();
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