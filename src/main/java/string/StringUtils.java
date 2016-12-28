package string;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static final String BASE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static boolean isLetter(String s) {
        Pattern pattern = Pattern.compile("^([a-zA-Z]+)?$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static String randomAlphabet(int length) {
        Random random = new Random(System.nanoTime());
        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            sb.append(BASE.charAt(random.nextInt(BASE.length())));
        }
        return sb.toString();
    }

    public static String reverse(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    public static String toHex(String string) {
        StringBuffer sb = new StringBuffer(2 * string.length());
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            sb.append(Integer.toHexString((c & 0xF0) >> 4));
            sb.append(Integer.toHexString((c & 0x0F)));
        }
        return sb.toString();
    }
}