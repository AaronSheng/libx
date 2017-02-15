package cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by aaron on 2/15/17.
 */
public class SHA1Utils {
    private static final String ALGORITHM_SHA1 = "SHA-1";

    public static String sha1(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM_SHA1);
            md.update(str.getBytes());

            byte[] after = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < after.length; i++) {
                sb.append(Integer.toHexString((after[i] & 0xF0) >> 4));
                sb.append(Integer.toHexString((after[i] & 0xF) >> 0));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
    }
}
