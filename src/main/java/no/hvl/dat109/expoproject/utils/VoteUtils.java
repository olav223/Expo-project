package no.hvl.dat109.expoproject.utils;

/**
 * Utility class for generating voter IDs
 */
public final class VoteUtils {

    /**
     * Generates a random voter ID
     *
     * @return The voter ID
     */
    public static String generateVoterID() {
        return PasswordUtils.generateSalt();
    }
}
