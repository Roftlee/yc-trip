package com.yc.trip.api.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlUtil {

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLEncode时字符集有误", e);
        }
    }

    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("URLDecode时字符集有误", e);
        }
    }

}
