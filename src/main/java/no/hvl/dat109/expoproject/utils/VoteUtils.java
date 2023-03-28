package no.hvl.dat109.expoproject.utils;

public class VoteUtils {

    public static String generateVoterID() {
        return PasswordUtils.generateSalt();
    }
}
