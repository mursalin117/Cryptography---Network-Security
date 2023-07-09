import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {

    public static String md5(String input) throws NoSuchAlgorithmException {
        // Get an instance of the MessageDigest class with the MD5 algorithm
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Convert the input string to bytes using UTF-8 encoding
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

        // Update the digest with the input bytes
        md.update(inputBytes);

        // Calculate the MD5 hash
        byte[] digest = md.digest();

        // Convert the hash bytes to a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "The quick brown fox jumps over the lazy dog";
        try {
            String hash = md5(input);
            System.out.println("Input: " + input);
            System.out.println("MD5 hash: " + hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
