package digest;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSAUtils is a uitl for rsa signature and encrypt.
 * It depends on commons-codec library for base64 decode and encode.
 * When use RSAUtils, you should make sure of removing begin and end of pem file.
 */
public class RSAUtils {
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public static byte[] publicEncrypt(byte[] plainTextData, String publicKeyStr) throws DigestException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
            PublicKey publicKey = keyFactory.generatePublic(pubX509);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(plainTextData);
        } catch (Exception e) {
            throw new DigestException(e);
        }
    }

    public static byte[] privateEncrypt(byte[] plainTextData, String privateKeyStr) throws DigestException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
            PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(plainTextData);
        } catch (Exception e) {
            throw new DigestException(e);
        }
    }

    public static byte[] publicDecrypt(byte[] cipherData, String publicKeyStr) throws DigestException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
            PublicKey publicKey = keyFactory.generatePublic(pubX509);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(cipherData);
        } catch (Exception e) {
            throw new DigestException(e);
        }
    }

    public static byte[] privateDecrypt(byte[] cipherData, String privateKeyStr) throws DigestException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
            PrivateKey privateKey = keyFactory.generatePrivate(priPKCS8);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(cipherData);
        } catch (Exception e) {
            throw new DigestException(e);
        }
    }

    public static String sign(String content, String privateKey, String encode) throws DigestException {
        try {
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes(encode));

            return new String(Base64.encodeBase64(signature.sign()));
        } catch (Exception e) {
            throw new DigestException(e);
        }
    }

    public static String sign(String content, String privateKey) throws DigestException {
        try {
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes());

            return new String(Base64.encodeBase64(signature.sign()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new DigestException(e);
        }
    }

    public static boolean verifySign(String content, String sign, String publicKeyStr, String encode) throws DigestException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
            PublicKey publicKey = keyFactory.generatePublic(pubX509);

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(publicKey);
            signature.update(content.getBytes(encode));

            return signature.verify( Base64.decodeBase64(sign));
        }
        catch (Exception e) {
            throw new DigestException(e);
        }
    }

    public static boolean verifySign(String content, String sign, String publicKeyStr) throws DigestException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
            PublicKey publicKey = keyFactory.generatePublic(pubX509);

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(publicKey);
            signature.update(content.getBytes());

            return signature.verify(Base64.decodeBase64(sign));
        }
        catch (Exception e) {
            throw new DigestException(e);
        }
    }
}
