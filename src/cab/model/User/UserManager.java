package cab.booking.system.model.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, String> passwordTable;

    public UserManager() {
        this.passwordTable = new HashMap<>();
    }

    public void registerUser(String phoneNumber, String password) {
        if (isPasswordStrong(password)) {
            String hashedPassword = hashPassword(password);
            if (!passwordTable.containsKey(phoneNumber)) {
                passwordTable.put(phoneNumber, hashedPassword);
                System.out.println("User registered successfully.");
            } else {
                System.out.println("User with phone number " + phoneNumber + " already exists.");
            }
        } else {
            System.out.println("Password is not strong enough. It should have at least 8 characters and include both letters and digits.");
        }
    }

    private boolean isPasswordStrong(String password) {
        int minLength = 8;
        boolean hasLetter = false;
        boolean hasDigit = false;
        if (password.length() >= minLength) {
            for (char ch : password.toCharArray()) {
                if (Character.isLetter(ch)) {
                    hasLetter = true;
                } else if (Character.isDigit(ch)) {
                    hasDigit = true;
                }
                if (hasLetter && hasDigit) {
                    break;
                }
            }
        }
        return hasLetter && hasDigit;
    }
    public boolean verifyPassword(String phoneNumber, String password) {
        String storedPassword = passwordTable.get(phoneNumber);
        if (storedPassword == null) {
            return false;
        }
        String enteredPasswordHash = hashPassword(password);
        return storedPassword.equals(enteredPasswordHash);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexStringBuilder.append('0');
                }
                hexStringBuilder.append(hex);
            }
            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}

