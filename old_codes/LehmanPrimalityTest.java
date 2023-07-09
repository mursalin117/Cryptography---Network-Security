import java.math.BigInteger;
import java.util.Random;

public class LehmanPrimalityTest {

    public static boolean isPrime(BigInteger n, int k) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }
        if (n.compareTo(BigInteger.valueOf(3)) <= 0) {
            return true;
        }
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        // Generate random numbers a_i
        Random rand = new Random();
        BigInteger[] a = new BigInteger[k];
        for (int i = 0; i < k; i++) {
            a[i] = new BigInteger(n.bitLength(), rand);
            if (a[i].compareTo(BigInteger.TWO) < 0 || a[i].compareTo(n.subtract(BigInteger.TWO)) > 0) {
                i--;
            }
        }

        // Calculate Legendre symbol
        BigInteger legendre = BigInteger.ONE;
        for (int i = 0; i < k; i++) {
            legendre = legendre.multiply(a[i].modPow(n.subtract(BigInteger.ONE).divide(BigInteger.TWO), n)).mod(n);
        }

        // Check primality
        if (!legendre.equals(BigInteger.ONE)) {
            return false;
        }

        // Check Jacobi symbols
        for (int i = 0; i < k; i++) {
            BigInteger aiModN = a[i].mod(n);
            BigInteger ajModN = aiModN.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.valueOf(k)), n);
            BigInteger ajSquare = ajModN.multiply(ajModN).mod(n);
            BigInteger aiSquare = aiModN.multiply(aiModN).mod(n);
            if (!ajSquare.equals(aiSquare)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        BigInteger n = new BigInteger("7919"); // test prime number
        int k = 10; // number of random bases to test
        if (isPrime(n, k)) {
            System.out.println(n + " is prime");
        } else {
            System.out.println(n + " is composite");
        }
    }
}
