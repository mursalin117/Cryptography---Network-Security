import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger n, d, e;

    public RSA(int bitLength) {
        SecureRandom rnd = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, rnd);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, rnd);
        n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLength / 2, rnd);
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public byte[] encrypt(byte[] plaintext) {
        BigInteger message = new BigInteger(plaintext);
        BigInteger ciphertext = message.modPow(e, n);
        return ciphertext.toByteArray();
    }

    public byte[] decrypt(byte[] ciphertext) {
        BigInteger message = new BigInteger(ciphertext);
        BigInteger plaintext = message.modPow(d, n);
        return plaintext.toByteArray();
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(2048);

        byte[] plaintext = "Hello, this is my world!".getBytes();
        byte[] ciphertext = rsa.encrypt(plaintext);
        byte[] decrypted = rsa.decrypt(ciphertext);

        System.out.println(new String(plaintext));
        System.out.println(new String(ciphertext));
        System.out.println(new String(decrypted));

    }
}
