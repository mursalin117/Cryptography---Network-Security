import java.math.BigInteger;
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
            // Generate a random number a in the range [2, n-1]
            BigInteger a = randomInRange(BigInteger.TWO, n.subtract(BigInteger.ONE));
            // Calculate a^(n-1)/2 mod n
            BigInteger power = a.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.TWO), n);
            // If the result is not 1 or n-1, then n is composite
            if (!power.equals(BigInteger.ONE) && !power.equals(n.subtract(BigInteger.ONE))) {
                return false;
            }
        }
        // If all iterations pass, n is likely prime
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
        BigInteger n = new BigInteger("1");
        int iterations = 10;
        boolean prime = isPrime(n, iterations);
        System.out.println(n + " is prime: " + prime);
    }    
}
