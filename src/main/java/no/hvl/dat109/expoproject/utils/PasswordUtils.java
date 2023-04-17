package no.hvl.dat109.expoproject.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Utility class for hashing passwords and validating them
 */
public final class PasswordUtils {

    /**
     * Generates a random salt of length 32
     *
     * @return The salt
     */
    public static String generateSalt() {
        final byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return DatatypeConverter.printHexBinary(salt);
    }

    /**
     * Hashes the given password with the given salt
     *
     * @param passord The password to hash
     * @param salt    The salt to use
     * @return The hashed password
     * @throws IllegalArgumentException If password or salt is null
     * @throws RuntimeException         If hashing fails
     */
    public static String hashWithSalt(final String passord, final String salt) {
        if (passord == null || salt == null) {
            throw new IllegalArgumentException("Password and salt cannot be null");
        }

        final PBEKeySpec pbe = new PBEKeySpec(passord.toCharArray(), salt.getBytes(), 696, 256);
        SecretKeyFactory scf;
        byte[] keyhash;
        try {
            scf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            keyhash = scf.generateSecret(pbe).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        return DatatypeConverter.printHexBinary(keyhash);
    }

    /**
     * Validates the given password with the given salt and password hash
     *
     * @param passord     The password to validate
     * @param salt        The salt to use
     * @param passordHash The password hash to compare with
     * @return true if the password is valid, otherwise false
     * @throws IllegalArgumentException If password, salt or password hash is null
     */
    public static boolean validate(final String passord, final String salt, final String passordHash) {
        if (passord == null || salt == null || passordHash == null) {
            throw new IllegalArgumentException("Password and salt cannot be null");
        }
        return passordHash.equals(hashWithSalt(passord, salt));
    }

}
