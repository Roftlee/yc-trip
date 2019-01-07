package com.yc.trip.api.core.util;

import com.yc.trip.api.core.constants.CharsetConstants;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 算法、摘要、签名、验签、加密、解密
 *
 * @author qatang
 * @since 2015-07-15 10:58
 */
public class CoreCodecUtils {
    private final static Logger logger = LoggerFactory.getLogger(CoreCodecUtils.class);

    public static final String KEY_SHA1 = "SHA1";
    public static final String KEY_MD5 = "MD5";
    public static final String KEY_RSA = "RSA";
    public static final String KEY_DESEDE = "DESede";

    private static final String SIGNATURE_ALGORITHM_SHA = "SHA1WithRSA";
    private static final String SIGNATURE_ALGORITHM_MD5 = "MD5WithRSA";

    /**
     * 加密/解密算法/工作模式/填充方式
     */
    public static final String CIPHER_ALGORITHM_DESEDE = "DESede/ECB/PKCS5Padding";

    public static String encryptMD5(byte[] source) {
        return DigestUtils.md5Hex(source);
    }

    /**
     * MD5加密
     *
     * @param source   源数据
     * @param encoding 编码
     * @return md5摘要
     */
    public static String encryptMD5(String source, String encoding) {
        String result = null;
        try {
            result = encryptMD5(source.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }

        if (result == null) {
            throw new RuntimeException("MD5加密失败！");
        }
        return result;
    }

    /**
     * MD5加密
     *
     * @param source 源数据
     * @return 加密结果
     */
    public static String encryptMD5(String source) {
        return CoreCodecUtils.encryptMD5(source, CharsetConstants.CHARSET_UTF8);
    }

    /**
     * SHA1加密
     *
     * @param source   源数据
     * @param encoding 编码
     * @return sha1摘要
     */
    public static String encryptSHA1(String source, String encoding) {
        String result = null;
        try {
            result = DigestUtils.sha1Hex(source.getBytes(encoding));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }

        if (result == null) {
            throw new RuntimeException("SHA1加密失败！");
        }
        return result;
    }

    /**
     * SHA1加密
     *
     * @param source 源数据
     * @return 加密结果
     */
    public static String encryptSHA1(String source) {
        return CoreCodecUtils.encryptSHA1(source, CharsetConstants.CHARSET_UTF8);
    }

    /**
     * BASE64加密
     *
     * @param source 源数据
     * @return 加密结果
     */
    public static String encryptBASE64(byte[] source) {
        Base64 base64 = new Base64();
        return base64.encodeToString(source);
    }

    /**
     * BASE64解密
     *
     * @param source 源数据
     * @return 解密结果
     */
    public static byte[] decryptBASE64(String source) {
        Base64 base64 = new Base64();
        return base64.decode(source);
    }

    /**
     * HEX加密
     *
     * @param source 源数据
     * @return 加密结果
     */
    public static String encryptHEX(byte[] source) {
        Hex hex = new Hex();
        return new String(hex.encode(source));
    }

    /**
     * HEX解密
     *
     * @param source 源数据
     * @return 解密结果
     */
    public static byte[] decryptHEX(String source) {
        Hex hex = new Hex();
        try {
            return hex.decode(source.getBytes());
        } catch (DecoderException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * RSA用私钥对信息生成数字签名
     *
     * @param source     源数据
     * @param privateKey 私钥 使用base64编码之后的key
     * @return 签名结果
     */
    public static String signWithRSAByPrivateKey(byte[] source, String privateKey, String algorithm) {
        try {
            //解密私钥
            byte[] keyBytes = decryptBASE64(privateKey);

            //构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
            //指定加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
            //取私钥匙对象
            PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            //用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privateKey2);
            signature.update(source);
            return encryptBASE64(signature.sign());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * RSA用私钥对信息生成数字签名
     *
     * @param source     源数据
     * @param privateKey 私钥 使用base64编码之后的key
     * @param encoding   编码
     * @return 签名结果
     */
    public static String signWithRSAByPrivateKey(String source, String privateKey, String algorithm, String encoding) {
        try {
            return signWithRSAByPrivateKey(source.getBytes(encoding), privateKey, algorithm);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * RSA用私钥对信息生成数字签名
     *
     * @param source     源数据
     * @param privateKey 私钥
     * @return 签名结果
     */
    public static String signWithRSAByPrivateKey(String source, String privateKey) {
        return signWithRSAByPrivateKey(source, privateKey, SIGNATURE_ALGORITHM_SHA, CharsetConstants.CHARSET_UTF8);
    }

    /**
     * RSA用公钥验证数字签名
     *
     * @param source    源数据
     * @param publicKey 公钥 使用base64编码之后的key
     * @param sign      签名
     * @return 签名结果
     */
    public static boolean verifyWithRSAByPublicKey(byte[] source, String publicKey, String sign, String algorithm) {
        try {
            //解密公钥
            byte[] keyBytes = decryptBASE64(publicKey);
            //构造X509EncodedKeySpec对象
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
            //指定加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
            //取公钥匙对象
            PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
            //用公钥对信息生成数字签名
            Signature signature = Signature.getInstance(algorithm);
            signature.initVerify(publicKey2);
            signature.update(source);
            //验证签名是否正常
            return signature.verify(decryptBASE64(sign));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * RSA用公钥验证数字签名
     *
     * @param source    源数据
     * @param publicKey 公钥 使用base64编码之后的key
     * @param sign      签名
     * @param encoding  编码
     * @return 签名结果
     */
    public static boolean verifyWithRSAByPublicKey(String source, String publicKey, String sign, String algorithm,
                                                   String encoding) {
        try {
            return verifyWithRSAByPublicKey(source.getBytes(encoding), publicKey, sign, algorithm);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * RSA用公钥验证数字签名
     *
     * @param source    源数据
     * @param publicKey 公钥 使用base64编码之后的key
     * @param sign      签名
     * @return 签名结果
     */
    public static boolean verifyWithRSAByPublicKey(String source, String publicKey, String sign) {
        return verifyWithRSAByPublicKey(source, publicKey, sign, SIGNATURE_ALGORITHM_SHA,
                CharsetConstants.CHARSET_UTF8);
    }

    /**
     * RSA公钥加密
     *
     * @param source    源数据
     * @param publicKey 公钥 使用base64编码之后的key
     * @return 加密后的结果
     */
    public static byte[] encryptWithRSAByPublicKey(byte[] source, String publicKey) {
        try {
            //解密公钥
            byte[] keyBytes = decryptBASE64(publicKey);
            //构造X509EncodedKeySpec对象
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
            //指定加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
            //取公钥匙对象
            PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
            //加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey2);

            return cipher.doFinal(source);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * RSA公钥解密
     *
     * @param source    源数据
     * @param publicKey 公钥 使用base64编码之后的key
     * @return 解密后的结果
     */
    public static byte[] decryptWithRSAByPublicKey(byte[] source, String publicKey) {
        try {
            //解密公钥
            byte[] keyBytes = decryptBASE64(publicKey);
            //构造X509EncodedKeySpec对象
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
            //指定加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
            //取公钥匙对象
            PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
            //解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey2);

            return cipher.doFinal(source);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * RSA私钥加密
     *
     * @param source     源数据
     * @param privateKey 私钥 使用base64编码之后的key
     * @return 加密后的结果
     */
    public static byte[] encryptWithRSAByPrivateKey(byte[] source, String privateKey) {
        try {
            //解密私钥
            byte[] keyBytes = decryptBASE64(privateKey);
            //构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
            //指定加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
            //取公钥匙对象
            PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            //加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey2);

            return cipher.doFinal(source);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * RSA私钥解密
     *
     * @param source     源数据
     * @param privateKey 私钥 使用base64编码之后的key
     * @return 解密后的结果
     */
    public static byte[] decryptWithRSAByPrivateKey(byte[] source, String privateKey) {
        try {
            //解密私钥
            byte[] keyBytes = decryptBASE64(privateKey);
            //构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
            //指定加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
            //取公钥匙对象
            PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            //解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey2);

            return cipher.doFinal(source);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 3des加密
     *
     * @param source 源数据
     * @param key    私钥 使用base64编码之后的key
     * @return 加密后的结果
     */
    public static byte[] encryptWithDESedeByKey(byte[] source, String key) {
        try {
            //解密私钥
            byte[] keyBytes = decryptBASE64(key);
            //构造DESedeKeySpec对象
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(keyBytes);
            //指定加密算法
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_DESEDE);
            //取公钥匙对象
            SecretKey secretKey = secretKeyFactory.generateSecret(deSedeKeySpec);
            //加密
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_DESEDE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return cipher.doFinal(source);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 3des解密
     *
     * @param source 源数据
     * @param key    私钥 使用base64编码之后的key
     * @return 解密后的结果
     */
    public static byte[] decryptWithDESedeByKey(byte[] source, String key) {
        try {
            //解密私钥
            byte[] keyBytes = decryptBASE64(key);
            //构造DESedeKeySpec对象
            DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(keyBytes);
            //指定加密算法
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_DESEDE);
            //取公钥匙对象
            SecretKey secretKey = secretKeyFactory.generateSecret(deSedeKeySpec);
            //解密
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_DESEDE);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            return cipher.doFinal(source);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("MD5");
        System.out.println(CoreCodecUtils.encryptMD5("卧槽卧槽卧槽卧槽卧槽卧槽"));
        System.out.println(CoreCodecUtils.encryptMD5("卧槽卧槽卧槽卧槽卧槽卧槽", CharsetConstants.CHARSET_GBK));
        System.out.println("-----------------");

        System.out.println("SHA1");
        System.out.println(CoreCodecUtils.encryptSHA1("卧槽卧槽卧槽卧槽卧槽卧槽"));
        System.out.println(CoreCodecUtils.encryptSHA1("卧槽卧槽卧槽卧槽卧槽卧槽", CharsetConstants.CHARSET_GBK));
        System.out.println("-----------------");

        System.out.println("BASE64");
        String base64 = CoreCodecUtils.encryptBASE64("卧槽".getBytes());
        System.out.println(base64);
        System.out.println(new String(CoreCodecUtils.decryptBASE64(base64)));
        System.out.println("-----------------");

        System.out.println("HEX");
        String hex = CoreCodecUtils.encryptHEX("卧槽".getBytes());
        System.out.println(hex);
        System.out.println(new String(CoreCodecUtils.decryptHEX(hex)));
        System.out.println("-----------------");

        System.out.println("rsa sign");

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(CoreCodecUtils.KEY_RSA);
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //公钥
        RSAPublicKey publicKey1 = (RSAPublicKey) keyPair.getPublic();
        //私钥
        RSAPrivateKey privateKey1 = (RSAPrivateKey) keyPair.getPrivate();

        System.out.println(CoreCodecUtils.encryptBASE64(publicKey1.getEncoded()));
        System.out.println("-----------------");
        System.out.println(CoreCodecUtils.encryptBASE64(privateKey1.getEncoded()));
        System.out.println("-----------------");

        String source = "aaaaaaa";
        // key length 1024
        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFpFJpWXVR+0D9fV6/qk5H8fcrUHWsfvYqbn6AuEBy7B+lRboc5VbNU55qJVvpgXGzuiclCK1FHdxNn4BLW2cb4trhBk4kCZkDowxT4r2LktZkU7byySzm+V6qbKiftwKy338oP1oCAavhqvshrlZLZfeHBTLpNRwLenjFMFv91QIDAQAB";
        String pri = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMWkUmlZdVH7QP19Xr+qTkfx9ytQdax+9ipufoC4QHLsH6VFuhzlVs1TnmolW+mBcbO6JyUIrUUd3E2fgEtbZxvi2uEGTiQJmQOjDFPivYuS1mRTtvLJLOb5XqpsqJ+3ArLffyg/WgIBq+Gq+yGuVktl94cFMuk1HAt6eMUwW/3VAgMBAAECgYEAlO9REwZQvGij/uG8xp4lw115LvDA3C4Ifs0pEwLFvlRlPHwg1amVEcFTfRWN/5aGAjPjT6sMNytbWjs5yI+mUDAwyqtTrCqnSrgJWuqJN0GSiYXlxJFwaGB6hNTZUVxljhJ7ePkG2wISJHXDwqBycjmOwNvUmblQunJyrAwGcgECQQDm6K5Viy5o2iiSUVQDa6rk+YOLuJgpiB8HnrOF5rMfed8Knpa3aU7/iJSiJQvCF7Edojb8EeMdt5n1WHEOvFRBAkEA2x474BOBeIs9ebBQ9KULmIHY7lCYcAgsY3wnzAJTKD885Mh0+hMo9nqki6O1dtw78KQ3USSk8JMmP0vw/DH0lQJAVV7VhZ8Nq4ps0Zvr+c/GwR1W+0NmToSbJFASg4EF3K9MPTrg8jKnGGbAvSoBLONhgllFBvD+DTCiZUkrk40hgQJAKH0ntzojfHDORUQtUWeGAwJLYY4G/PylpyRz/iCX2SIGUIgyBVuxAAqdiSgRCgucD9VoW1gYGlQ2hw+zUgaxGQJBAKCwx+HuEo0W7HXF+nF1f/r1wIr0fMm82p9M2vOVbZO9Kl4nG9Opo9oFzxdl52/TsIYpZLi+0mxUj+Ec7E/CfJY=";

        String sign = CoreCodecUtils.signWithRSAByPrivateKey(source, pri);
        System.out.println(sign);
        System.out.println(CoreCodecUtils.verifyWithRSAByPublicKey(source, pub, sign));

        // key length 2048
        String pub2 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiX5Lf4S9N9x8EzWoH8E53z+Zr0Q7PIxqaAf0oAAXG36Ucu2eKMwFm5+AqL064qGKnWOQ/Ogl7n8yfOhfqRHv0MeMA+H7SQoqXLe8v9sJ8bxkvst+i6Cy9erucgLO80tdzSIZ79P9ZqiDNWFP0G0uVrOlXXxqBVlIR3rebYsry7Y/7nf93bUpMiHHn59KKPOcGCV9ZwMWVczbllDLR8eHkqqcyvY9Q7y4f8aoVeft4++mq9C8dBPml51gruEbISA/vY6nWSIjC+TZd7JmXo4yyhsyum3UlwrElnHoyzdQTqoSSFjINhhcwXvrD8G0EXz/LvMUcXxUj6kkKqiLRlbfzQIDAQAB";
        String pri2 = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJfkt/hL033HwTNagfwTnfP5mvRDs8jGpoB/SgABcbfpRy7Z4ozAWbn4CovTrioYqdY5D86CXufzJ86F+pEe/Qx4wD4ftJCipct7y/2wnxvGS+y36LoLL16u5yAs7zS13NIhnv0/1mqIM1YU/QbS5Ws6VdfGoFWUhHet5tiyvLtj/ud/3dtSkyIcefn0oo85wYJX1nAxZVzNuWUMtHx4eSqpzK9j1DvLh/xqhV5+3j76ar0Lx0E+aXnWCu4RshID+9jqdZIiML5Nl3smZejjLKGzK6bdSXCsSWcejLN1BOqhJIWMg2GFzBe+sPwbQRfP8u8xRxfFSPqSQqqItGVt/NAgMBAAECggEAd9OVwMDSl4iUqbktmN8xrJslOVZhudc2NQwZ6geqG9ApWDA2kp+ck5Bi9KxWUYUv5RMNHRzFUiGQwsNR+2sicfY21HtyIKm3Wa7X1Oz0sbTaqF15H/8heKkSvlIsQmUAjvKVYCqEwZ9DGpaAlMlShUVzbRIs5BR3pbQzfYy40oPr8mn4XAVRyBVgl3IdRGDQQEWmG+rFdStZjYjg0HdZzScvubdCInJxKMWx7pvXzNLQZvetfzcw19+SZi21h6ivbLRHUCyF8m1lllZbdVlj9qEBxSNW1lJEp3m48MnrmxH/kfShNrAyPvu9tPMPrvTEcjaVixw/iIok6matoPtbkQKBgQDb9bKnfSaRJMCBK4YrLd3ViJhIeitNXdG6q0L1y78aZH3lqb/PUKmbwe5dWmg5wM9qxMOeHDovwMmOzZQi2lC2hECXluTUFbIMt+9Kf/+zAMT2scSYkjNqOGh77JHhC7DJ1jr0zDwssZt9SmpUkfbxLxsyJJK0gMJTVI3r5yGmuwKBgQCgBYJl7hzCH74R2Qp8/H35CtY8iaiuTzL7OomsR2mI3A7cTyrwI1pzhMiqRUjHMTAhii5/ygP9OvlHsqaBdXNVkNnLrrdWdeM7vsImjldPQQ78MvMCkZw6yZ1Z0YcZE0JPwsH23G8yV7T8MPG75Geml8FUxA6QWXDEu3I9FvzfFwKBgBXfb92R52Tlh7MQle3zSKz0QVhrtaSi4Lk0UJtKoH1KYu6Um7tM5SDU/xQPam/byYRvfphZbDvVavWlYca5eO89ZYLtgWHJqoPzQnthERdGy09QzCDz4OL+Kv2vv63mULUxdXlkWdOD8ddasAoq3aiMK6Mn2eLTLv/EV7yOpl0DAoGAfzIB5X2wlElg7pTjtleI1nVbt0EAuJUxg+qS1kLpSyUIFuxCyCCB3ELWsiliYRrjFDXfIip0o0vGK8yAd3ThWUlLJZDaIDqShcHqV3VbRx8Ch0sESK1vTndFwMhAp5jcY8P+I3gyVwTKUo+hBmXWUeBch0FUBj9h2K7abl6gX78CgYAGMKu/AySl6gaL0FJ+cnziiRdEq0UQqmJviTEWhT8XWL4btSIUZSLpRLQuhSzPtmzvK2n1kSiYuwglWFEbBFRVmyidBZPW0Bp+ZLihqYbJ0eYHtyof6EBF/8QXtfqSnIcaEcQP4aRxplOQJadZOVZ1082pHvU5pFtUYwh1PLy6vw==";

        String sign2 = CoreCodecUtils.signWithRSAByPrivateKey(source, pri2);
        System.out.println(sign2);
        System.out.println(CoreCodecUtils.verifyWithRSAByPublicKey(source, pub2, sign2));

        System.out.println("-----------------");
        System.out.println("rsa 公钥加密，私钥解密");

        byte[] encryptData = encryptWithRSAByPublicKey("卧槽".getBytes(), pub);
        System.out.println(encryptHEX(encryptData));
        byte[] decryptData = decryptWithRSAByPrivateKey(encryptData, pri);
        System.out.println(new String(decryptData));

        System.out.println("-----------------");
        System.out.println("rsa 私钥加密，公钥解密");

        byte[] encryptData2 = encryptWithRSAByPrivateKey("卧槽2".getBytes(), pri);
        System.out.println(encryptHEX(encryptData2));
        byte[] decryptData2 = decryptWithRSAByPublicKey(encryptData2, pub);
        System.out.println(new String(decryptData2));

        System.out.println("-----------------");
        System.out.println("3des 加密，解密");

        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_DESEDE);
        //初始化密钥生成器
        keyGenerator.init(168);
        //生成密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //获取二进制密钥编码形式
        byte[] desedeSecretKey = secretKey.getEncoded();

        String desedeKey = encryptBASE64(desedeSecretKey);
        System.out.println("生成的DESede密钥是：" + desedeKey);

        byte[] encryptData3 = encryptWithDESedeByKey("卧槽desede".getBytes(), desedeKey);
        System.out.println(encryptBASE64(encryptData3));
        byte[] decryptData3 = decryptWithDESedeByKey(encryptData3, desedeKey);
        System.out.println(new String(decryptData3));
    }
}