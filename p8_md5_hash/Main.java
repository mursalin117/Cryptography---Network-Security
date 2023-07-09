import java.math.BigInteger;
import java.security.MessageDigest;

public class Main {

    public static String getMD5 (String input) throws Exception {
        // Static getInstance method is called with hashing MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        // digest() method is called to calculate message digest
		// of an input digest() return array of byte
        byte[] messageDigest = md.digest(input.getBytes());
        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);
        // Convert message digest into hex value
        String hashText = no.toString(16);
        while (hashText.length() < 32) {
            hashText = "0" + hashText;
        }
        return hashText;
    }

    public static void main(String[] args) {
        try {
            String s = "Hello";
            String hash = getMD5(s);
            System.out.println("MD5 hash is: " + hash);    
        } 
        // For specifying wrong message digest algorithms
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }    
}
