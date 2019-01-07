package com.yc.trip.api.core.util;

import com.yc.trip.api.core.constants.CharsetConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author qatang
 * @since 2015-07-20 11:05
 */
public class CoreStringUtils {
    protected static final transient Logger logger = LoggerFactory.getLogger(CoreStringUtils.class);

    public static String generateRandomStr(int len) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Random random = new Random();
            result.append(chars.charAt(random.nextInt(chars.length() - 1)));
        }
        return result.toString();
    }

    public static String generateRandomCode(int len) {
        String chars = "0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            Random random = new Random();
            result.append(chars.charAt(random.nextInt(chars.length() - 1)));
        }
        return result.toString();
    }

    /**
     * 检验中国的手机号格式
     *
     * @param phone 不含+86/8086等前缀的11位手机号
     * @return 检测通过则返回true
     */
    public static boolean checkChinaMobilePhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            logger.error("手机号不能为空, phone={}", phone);
            return false;
        }

        if (phone.length() != 11) {
            logger.error("手机号位数不正确, phone=\"{}\"", phone);
            return false;
        }

        if (!isNumeric(phone)) {
            logger.error("手机号必须全为数字, phone={}", phone);
            return false;
        }

        if (!StringUtils.startsWithAny(phone, "13", "14", "15", "17", "18")) {
            logger.error("手机号前两位非法, phone={}", phone);
            return false;
        }

        return true;
    }

    /**
     * 检测字符串是否仅包含数字
     *
     * @param s 待测试字符串
     * @return 检测通过返回true
     */
    public static boolean isNumeric(String s) {
        return StringUtils.containsOnly(s, "0123456789");
    }

    public static String uniqueId(String prefix) {
        SecureRandom sec = new SecureRandom();
        byte[] sbuf = sec.generateSeed(8);
        ByteBuffer bb = ByteBuffer.wrap(sbuf);

        long time = System.currentTimeMillis();
        String uniqid = String.format("%s%08x%05x", prefix, time / 1000, time);
        uniqid += "." + String.format("%.8s", "" + bb.getLong() * -1);
        return uniqid;
    }

    /**
     * 按照字节截取字符串<br/>
     * 如: str= 123456ABC, byteLen=7, 则return = 123456A
     *
     * @param str     被截取的字符串
     * @param byteLen 字节数
     * @param charset 字符集
     * @return
     */
    public static String subStrByByteLen(String str, int byteLen, Charset charset) {
        if (str == null) {
            return null;
        }
        if (byteLen <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.toString().getBytes(charset).length > byteLen) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 按照字节截取字符串, 默认UTF-8编码<br/>
     * 如: str= 123456ABC, byteLen=7, 则return = 123456A
     *
     * @param str     被截取的字符串
     * @param byteLen 字节数
     * @return
     */
    public static String subStrByByteLen(String str, int byteLen) {
        return subStrByByteLen(str, byteLen, Charset.forName(CharsetConstants.CHARSET_DEFAULT));
    }


    /**
     * 获取字符串真实长度，中文占2个长度，其他为1个长度
     *
     * @param value
     * @return
     */
    public static int realLenth(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(CoreStringUtils.generateRandomStr(16));
        System.out.println(checkChinaMobilePhone("18610116725"));
        System.out.println(checkChinaMobilePhone("08610116725"));

        for (int i = 0; i < 100; i++) {
            String uniqueId = uniqueId("lh");
            System.out.println(uniqueId);
            String base64Id = CoreCodecUtils.encryptBASE64(uniqueId.getBytes(CharsetConstants.CHARSET_UTF8));
            System.out.println(base64Id);
            System.out.println(URLEncoder.encode(base64Id, CharsetConstants.CHARSET_UTF8));
            System.out.println(base64Id.length());
        }
    }
}
