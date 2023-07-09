import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5_v3 {

    // Constants for the MD5 algorithm
    private static final int BLOCK_SIZE = 64;
    private static final int[] T = new int[64];
    static {
        for (int i = 0; i < 64; i++) {
            T[i] = (int) (long) ((1L << 32) * Math.abs(Math.sin(i + 1)));
        }
    }
    private static final int[] S = { 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
            5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
            4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
            6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21 };

    // Perform MD5 hash on the input data
    public static byte[] hash(byte[] data) throws NoSuchAlgorithmException {
        // Initialize variables
        int a0 = 0x67452301;
        int b0 = 0xEFCDAB89;
        int c0 = 0x98BADCFE;
        int d0 = 0x10325476;
        int[] X = new int[16];
        int n = data.length;
        int len = (n + 8) / BLOCK_SIZE + 1; // Length of padding in blocks
        int[] M = new int[len * 16];
        
        // Copy data into padded array
        for (int i = 0; i < n; i++) {
            M[i] = data[i] & 0xFF;
        }
        M[n] = 0x80;
        
        // Add length padding
        long bitLen = (long) n * 8;
        M[M.length - 2] = (int) bitLen;
        M[M.length - 1] = (int) (bitLen >>> 32);
        
        // Process blocks
        for (int i = 0; i < len; i++) {
            // Copy block i into X
            for (int j = 0; j < 16; j++) {
                int k = i * 64 + j * 4;
                X[j] = (M[k] & 0xFF) | ((M[k + 1] & 0xFF) << 8) | ((M[k + 2] & 0xFF) << 16) | ((M[k + 3] & 0xFF) << 24);
            }
            
            // Initialize hash value for this block
            int A = a0;
            int B = b0;
            int C = c0;
            int D = d0;
            
            // Main loop
            for (int j = 0; j < 64; j++) {        
                int F = 0;
                int g = 0;
                if (j < 16) {
                    F = (B & C) | ((~B) & D);
                    g = j;
                } else if (j < 32) {
                    F = (D & B) | ((~D) & C);
                    g = (5 * j + 1) % 16;
                } else if (j < 48) {
                    F = B ^ C ^ D;
                    g = (3 * j + 5) % 16;
                } else {
                    F = C ^ (B | (~D));
                    g = (7 * j) % 16;
                }
                
                // Calculate new hash value for this round
                int dTemp = D;
                D = C;
                C = B;
                B = B + Integer.rotateLeft((A + F + T[j] + X[g]), S[j]);
                A = dTemp;
            }
            
            // Add this block's hash to result so far
            int[] resultInts = {a0, b0, c0, d0};
            for (int j = 0; j < resultInts.length; j++) {
                resultInts[j] += new int[] {A, B, C, D}[j];
            }
            
            // Convert result to byte array
            ByteBuffer buffer = ByteBuffer.allocate(resultInts.length * 4);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            for (int j = 0; j < resultInts.length; j++) {
                buffer.putInt(resultInts[j]);
            }
            return buffer.array();
        }

    // Utility function to convert byte array to hex string
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte element : b) {
            int value = element & 0xFF;
            String hexString = Integer.toHexString(value);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    // Test function
    public static void main(String[] args) {
        try {
            String testString = "Hello, world!";
            byte[] testBytes = testString.getBytes("UTF-8");
            byte[] hash = MD5.hash(testBytes);
            String hashString = byteArrayToHexString(hash);
            System.out.println(hashString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
