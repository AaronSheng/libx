package digest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    private static final String HEX = "0123456789abcdef";

    public static byte[] encrypt(byte[] src, byte[] key) throws DigestException {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(1, skeySpec);

            int srcLength = src.length;
            int paddingLen = 16 * ((src.length - 1) / 16 + 1);
            byte[] paddingSrc = new byte[paddingLen];

            System.arraycopy(src, 0, paddingSrc, 0, srcLength);
            for (int i = srcLength; i < paddingLen; i++) {
                paddingSrc[i] = 48;
            }

            return cipher.doFinal(paddingSrc);
        } catch (Exception exception) {
            throw new DigestException(exception.getMessage());
        }
    }

    public static byte[] decrypt(byte[] encrypted, byte[] key) throws DigestException {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(2, skeySpec);

            byte[] decrypted = cipher.doFinal(encrypted);

            int paddingLen = decrypted.length;
            int unPaddingLen = paddingLen;
            for (int i = paddingLen - 1; i >= 0; i--) {
                if (decrypted[i] != 48)
                    break;
                unPaddingLen--;
            }

            byte[] unPaddingDecrypted = new byte[unPaddingLen];
            System.arraycopy(decrypted, 0, unPaddingDecrypted, 0, unPaddingLen);

            return unPaddingDecrypted;
        } catch (Exception exception) {
            throw new DigestException(exception);
        }
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];

        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }

    public static String toHex(byte[] buf) {
        StringBuffer result = new StringBuffer(2 * buf.length);

        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    public static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b & 0xF0) >> 4));
        sb.append(HEX.charAt((b & 0x0F)));
    }
}
