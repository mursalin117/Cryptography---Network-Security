import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA_2 {
    // private final BigInteger ONE = new BigInteger("1");
    private final SecureRandom random = new SecureRandom();
    BigInteger p, q, n, phi, e, d;

    // Generate an RSA key pair with a given bit length
    public RSA_2(int bitLength) {
        // Choose two large prime numbers, p and q
        p = BigInteger.probablePrime(bitLength / 2, random);
        q = BigInteger.probablePrime(bitLength / 2, random);
        
        // Compute the modulus n = p * q
        n = p.multiply(q);

        // Compute the totient of n, φ(n) = (p - 1) * (q - 1)
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Choose an encryption exponent e that is relatively prime to φ(n)
        // e = new BigInteger("65537"); // a common choice for e
        e = BigInteger.probablePrime(bitLength / 2, random);
        
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }

        // Compute the decryption exponent d, which is the multiplicative inverse of e modulo φ(n)
        d = e.modInverse(phi);
    }

    // Encrypt a message using a given public key
    public byte[] encrypt(byte[] message, BigInteger n, BigInteger e) {
        BigInteger m = new BigInteger(1, message);
        BigInteger c = m.modPow(e, n);
        return c.toByteArray();
    }

    // Decrypt a message using a given private key
    public byte[] decrypt(byte[] ciphertext, BigInteger n, BigInteger d) {
        BigInteger c = new BigInteger(1, ciphertext);
        BigInteger m = c.modPow(d, n);
        return m.toByteArray();
    }

    public BigInteger[] getPublicKey() {
        BigInteger[] publicKey = new BigInteger[2];
        publicKey[0] = n;
        publicKey[1] = e;
        return publicKey;
    }

    public BigInteger[] getPrivateKey() {
        BigInteger[] publicKey = new BigInteger[2];
        publicKey[0] = n;
        publicKey[1] = d;
        return publicKey;
    }

    public static void main(String[] args) {
        RSA_2 rsa = new RSA_2(2048);

        byte[] plaintext = "Hello, this is my world!".getBytes();
        BigInteger[] pubKey = rsa.getPublicKey(); 
        byte[] ciphertext = rsa.encrypt(plaintext, pubKey[0], pubKey[1]);

        BigInteger[] privKey = rsa.getPrivateKey();
        byte[] decrypted = rsa.decrypt(ciphertext, privKey[0], privKey[1]);

        System.out.println(new String(plaintext));
        System.out.println(new String(ciphertext));
        System.out.println(new String(decrypted));

    }
}











// import java.math.BigInteger;
// import java.security.SecureRandom;

// public class RSA_2 {
//     private final static BigInteger ONE = new BigInteger("1");
//     private final static SecureRandom random = new SecureRandom();

//     // Generate an RSA key pair with a given bit length
//     public static KeyPair generateKeyPair(int bitLength) {
//         // Choose two large prime numbers, p and q
//         BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
//         BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        
//         // Compute the modulus n = p * q
//         BigInteger n = p.multiply(q);

//         // Compute the totient of n, φ(n) = (p - 1) * (q - 1)
//         BigInteger phi = p.subtract(ONE).multiply(q.subtract(ONE));

//         // Choose an encryption exponent e that is relatively prime to φ(n)
//         BigInteger e = new BigInteger("65537"); // a common choice for e
        
//         while (phi.gcd(e).compareTo(ONE) > 0 && e.compareTo(phi) < 0) {
//             e = e.add(ONE);
//         }

//         // Compute the decryption exponent d, which is the multiplicative inverse of e modulo φ(n)
//         BigInteger d = e.modInverse(phi);

//         // Return the public and private key pairs
//         PublicKey publicKey = new PublicKey(n, e);
//         PrivateKey privateKey = new PrivateKey(n, d);
//         return new KeyPair(publicKey, privateKey);
//     }

//     // Encrypt a message using a given public key
//     public static byte[] encrypt(byte[] message, PublicKey publicKey) {
//         BigInteger m = new BigInteger(1, message);
//         BigInteger n = publicKey.getModulus();
//         BigInteger e = publicKey.getExponent();
//         BigInteger c = m.modPow(e, n);
//         return c.toByteArray();
//     }

//     // Decrypt a message using a given private key
//     public static byte[] decrypt(byte[] ciphertext, PrivateKey privateKey) {
//         BigInteger c = new BigInteger(1, ciphertext);
//         BigInteger n = privateKey.getModulus();
//         BigInteger d = privateKey.getExponent();
//         BigInteger m = c.modPow(d, n);
//         return m.toByteArray();
//     }
// }

// class KeyPair {
//     private PublicKey publicKey;
//     private PrivateKey privateKey;

//     public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
//         this.publicKey = publicKey;
//         this.privateKey = privateKey;
//     }

//     public PublicKey getPublicKey() {
//         return publicKey;
//     }

//     public PrivateKey getPrivateKey() {
//         return privateKey;
//     }
// }

// class PublicKey {
//     private BigInteger modulus;
//     private BigInteger exponent;

//     public PublicKey(BigInteger modulus, BigInteger exponent) {
//         this.modulus = modulus;
//         this.exponent = exponent;
//     }

//     public BigInteger getModulus() {
//         return modulus;
//     }

//     public BigInteger getExponent() {
//         return exponent;
//     }
// }

// class PrivateKey {
//     private BigInteger modulus;
//     private BigInteger exponent;

//     public PrivateKey(BigInteger modulus, BigInteger exponent) {
//         this.modulus = modulus;
//         this.exponent = exponent;
//     }

//     public BigInteger getModulus() {
//         return modulus;
//     }

//     public BigInteger getExponent() {
//         return exponent;
//     }
// }
