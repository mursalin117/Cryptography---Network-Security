import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Main {
    
    public static boolean isPrime(BigInteger n, int iterations) {

        // checking
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) {
            return true;
        }
        // Check if n is even or less than 2
        if (n.compareTo(BigInteger.TWO) < 0 || n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }
        // Run the Lehman primality test for the specified number of iterations
        for (int i = 0; i < iterations; i++) {

            // factoring n by 2
            int b = 0;
            BigInteger temp = n.subtract(BigInteger.ONE);
            while (temp.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                temp = temp.divide(BigInteger.TWO);
                b++;
            }
            BigInteger m = temp;

            // take a less than p
            // Generate a random number a in the range [2, n-1]
            BigInteger a = randomInRange(BigInteger.TWO, n.subtract(BigInteger.ONE));

            int j = 0;
            BigInteger z = a.modPow(m, n);
            if (z.equals(BigInteger.ONE) || z.equals(n.subtract(BigInteger.ONE))) {
                return true;
            }

            while (true) {
                if (j > 0 && z.equals(BigInteger.ONE)) {
                    return false;
                }
                j++;
                if (j < b && !z.equals(n.subtract(BigInteger.ONE))) {
                    z = z.modPow(BigInteger.TWO, n);
                } 
                else {
                    // if j == b (break) or z = n-1 (break and prime) 
                    break; 
                }
            }
            if (j == b && !z.equals(n.subtract(BigInteger.ONE))) {
                return false;
            }
        }

        return true;
    }

    private static BigInteger randomInRange(BigInteger min, BigInteger max) {
        BigInteger range = max.subtract(min);
        Random random = new Random();
        int maxNumBitLength = max.bitLength();

        BigInteger candidate;
        do {
            candidate = new BigInteger(maxNumBitLength, random);
        } while (candidate.compareTo(range) >= 0);

        return candidate.add(min);
    }

    public static void main(String[] args) {
        BigInteger n = new BigInteger("28871271685163");
        int iterations = 10;
        boolean prime = isPrime(n, iterations);
        System.out.println(n + " is prime: " + prime);
    }
}