import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5_v2 {

    // Constants for the MD5 algorithm
    private static final int[] S = {
            7, 12, 17, 22,
            5, 9, 14, 20,
            4, 11, 16, 23,
            6, 10, 15, 21
    };
    private static final int[] K = new int[64];
    static {
        for (int i = 0; i < 64; i++) {
            K[i] = (int) (Math.pow(2, 32) * Math.abs(Math.sin(i + 1)));
        }
    }

    public static byte[] hash(byte[] message) {
        // Initialize variables for the MD5 algorithm
        int a0 = 0x67452301;
        int b0 = 0xEFCDAB89;
        int c0 = 0x98BADCFE;
        int d0 = 0x10325476;
        long messageBits = message.length * 8;
        int numBlocks = (int) Math.ceil((messageBits + 64 + 1) / 512.0);
        int[] M = new int[numBlocks * 16];
        Arrays.fill(M, 0);
        for (int i = 0; i < message.length; i++) {
            M[i / 4] |= (message[i] & 0xFF) << (8 * (i % 4));
        }
        M[message.length / 4] |= 0x80 << (8 * (message.length % 4));
        M[numBlocks * 16 - 2] = (int) (messageBits & 0xFFFFFFFF);
        M[numBlocks * 16 - 1] = (int) (messageBits >>> 32);

        // Main loop of the MD5 algorithm
        for (int i = 0; i < numBlocks; i++) {
            int[] X = Arrays.copyOfRange(M, i * 16, (i + 1) * 16);
            int A = a0;
            int B = b0;
            int C = c0;
            int D = d0;
            for (int j = 0; j < 64; j++) {
                int F, g;
                if (j < 16) {
                    F = (B & C) | (~B & D);
                    g = j;
                } else if (j < 32) {
                    F = (D & B) | (~D & C);
                    g = (5 * j + 1) % 16;
                } else if (j < 48) {
                    F = B ^ C ^ D;
                    g = (3 * j + 5) % 16;
                } else {
                    F = C ^ (B | ~D);
                    g = (7 * j) % 16;
                }
                int temp = D;
                D = C;
                C = B;
                B = B + Integer.rotateLeft((A + F + K[j] + X[g]), S[j]);
                A = temp;
            }
            a0 += A;
            b0 += B;
            c0 += C;
            d0 += D;
        }

        // Convert the result to a byte array and return it
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(a0);
        buffer.putInt(b0);
        buffer.putInt(c0);
        buffer.putInt(d0);
        return buffer.array();
    }
}

// The MD5 algorithm is a widely used cryptographic hash function that produces
// a 128-bit hash value.
// This implementation of the MD5 algorithm takes in a message as a byte array
// and returns a 16-byte hash value.
// The algorithm works by dividing the message into 512-bit blocks, and
// processing each block through a series of operations,
// using a set of fixed constants and values that are derived from the message
// itself.
// The resulting hash value is a unique representation of the input message, and
// is typically used for message integrity checking,
// digital signatures, and other cryptographic applications.
