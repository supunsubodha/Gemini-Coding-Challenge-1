import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
    public static String generateSHA256Hash(String rawPassword) {
        try {
            // 1. Get the SHA-256 algorithm instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 2. Perform the hashing (returns an array of bytes)
            byte[] encodedHash = digest.digest(rawPassword.getBytes());

            // 3. Convert the byte array into a readable Hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Pad with leading zero if needed
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: Hashing algorithm not found.");
            return null;
        }
    }
}
