import java.math.BigInteger;
import java.security.SecureRandom;

public class Main {

    private static final BigInteger PRIME = new BigInteger("23");
    private static final BigInteger PRIMITIVE_ROOT = new BigInteger("5");
    private static final SecureRandom RANDOM = new SecureRandom();

    private static BigInteger generatePrivateKey() {
        // Generate a random private key in the range [2, PRIME-2]
        BigInteger privateKey;
        do {
            privateKey = new BigInteger (PRIME.bitLength(), RANDOM);
        } while (privateKey.compareTo(BigInteger.TWO) < 0 || privateKey.compareTo(PRIME.subtract(BigInteger.TWO)) > 0);
        
        return privateKey;
    }        

    private static BigInteger generatePublicKey (BigInteger privateKey) {
        // Compute the public key using the formula: public_key = (generator^private_key) % prime
        return PRIMITIVE_ROOT.modPow(privateKey, PRIME);
    }

    private static BigInteger generateSharedKey (BigInteger publicKey, BigInteger privateKey) {
        // Compute the shared secret key using the formula: shared_secret = (public_key^private_key) % prime
        return publicKey.modPow(privateKey, PRIME);
    }

    public static void main(String[] args) {
        // Alice generates her private key
        BigInteger alicePrivateKey = generatePrivateKey();
        // Bob generates his private key
        BigInteger bobPrivateKey = generatePrivateKey();
        // Alice computes her public key
        BigInteger alicePublicKey = generatePublicKey(alicePrivateKey);
        // Bob computes his public key
        BigInteger bobPublicKey = generatePublicKey(bobPrivateKey);
        // Alice and Bob exchange their public keys

        // Alice computes the shared secret key using Bob's public key
        BigInteger aliceSharedKey = generateSharedKey(bobPublicKey, alicePrivateKey);
        // Bob computes the shared secret key using Alice's public key
        BigInteger bobSharedKey = generateSharedKey(alicePublicKey, bobPrivateKey);
        // Verify that the shared secrets match
        System.out.println("Alice's shared key: " + aliceSharedKey);        
        System.out.println("Bob's shared key: " + bobSharedKey);        
        System.out.println("Shared key match: " + aliceSharedKey.compareTo(bobSharedKey));        
    }    
}
