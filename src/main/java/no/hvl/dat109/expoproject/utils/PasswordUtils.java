package no.hvl.dat109.expoproject.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordUtils {

    public static String genererSalt() {
        final byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return DatatypeConverter.printHexBinary(salt);
    }

    public static String hashMedSalt(String passord, String salt) {
        if (passord == null || salt == null) {
            throw new IllegalArgumentException("Passord og salt kan ikke være null");
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

    public static boolean validerMedSalt(String passord, String salt, String passordHash) {
        assert passord != null && salt != null && passordHash != null;
        return passordHash.equals(hashMedSalt(passord, salt));
    }

}
