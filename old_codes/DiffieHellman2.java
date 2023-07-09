import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman2 {

    private static final BigInteger PRIME = new BigInteger("23");
    private static final BigInteger GENERATOR = new BigInteger("5");
    private static final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        // Alice generates her private key
        BigInteger alicePrivate = generatePrivateKey();

        // Bob generates his private key
        BigInteger bobPrivate = generatePrivateKey();

        // Alice computes her public key
        BigInteger alicePublic = computePublicKey(alicePrivate);

        // Bob computes his public key
        BigInteger bobPublic = computePublicKey(bobPrivate);

        // Alice and Bob exchange their public keys

        // Alice computes the shared secret key using Bob's public key
        BigInteger aliceSharedSecret = computeSharedSecret(alicePrivate, bobPublic);

        // Bob computes the shared secret key using Alice's public key
        BigInteger bobSharedSecret = computeSharedSecret(bobPrivate, alicePublic);

        // Verify that the shared secrets match
        System.out.println("Alice's shared secret: " + aliceSharedSecret);
        System.out.println("Bob's shared secret: " + bobSharedSecret);
        System.out.println("Shared secrets match: " + aliceSharedSecret.equals(bobSharedSecret));
    }

    private static BigInteger generatePrivateKey() {
        // Generate a random private key in the range [2, PRIME-2]
        BigInteger privateKey;
        do {
            privateKey = new BigInteger(PRIME.bitLength(), RANDOM);
        } while (privateKey.compareTo(BigInteger.TWO) < 0 || privateKey.compareTo(PRIME.subtract(BigInteger.TWO)) > 0);
        return privateKey;
    }

    private static BigInteger computePublicKey(BigInteger privateKey) {
        // Compute the public key using the formula: public_key = (generator^private_key) % prime
        return GENERATOR.modPow(privateKey, PRIME);
    }

    private static BigInteger computeSharedSecret(BigInteger privateKey, BigInteger publicKey) {
        // Compute the shared secret key using the formula: shared_secret = (public_key^private_key) % prime
        return publicKey.modPow(privateKey, PRIME);
    }
}
