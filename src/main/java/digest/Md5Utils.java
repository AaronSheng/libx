package digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    private static final String ALGORITHM_MD5 = "MD5";

    public static String md5(String str) throws DigestException {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
            md.update(str.getBytes());

            byte[] after = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < after.length; i++) {
                sb.append(Integer.toHexString((after[i] & 0xF0) >> 4));
                sb.append(Integer.toHexString((after[i] & 0xF) >> 0));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException exception) {
           throw new DigestException(exception);
        }
    }
}