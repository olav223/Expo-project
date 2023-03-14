package no.hvl.dat109.expoproject.interfaces.utils;

public interface IPasswordUtil {
    /**
     * Generate and return salt for hashing
     * @return generated salt
     */
    String generateSalt();

    /**
     *
     * @param password
     * @param salt
     * @return hashed password with salt
     */
    String hash(String password, String salt);

    /**
     * Validates a given password
     * @param password
     * @param salt
     * @param hash
     * @return true if password is valid, false else
     */
    boolean Validate(String password, String salt, String hash);
}
