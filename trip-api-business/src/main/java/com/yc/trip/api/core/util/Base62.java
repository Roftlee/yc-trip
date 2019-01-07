package com.yc.trip.api.core.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base62编解码
 * @author zhan.shu
 *
 */
public final class Base62 {

	private static final String CHARSET_UTF8 = "UTF-8";

	/**
	 * 编码
	 * 
	 * @param target
	 * @return
	 */
	public static String encode(String target) {
		String result = null;
		try {
			result = Base64.getEncoder().encodeToString(target.getBytes(CHARSET_UTF8));
		} catch (UnsupportedEncodingException e) {
		}
		return result.replace("i", "ii").replace("+", "ia").replace("/", "is").replace("=", "ie").replace(" ", "ib");
	}

	/**
	 * 反编码
	 * 
	 * @param encodeString
	 * @return
	 */
	public static String decode(String encodeString) {
		String result = null;
		String target = encodeString.replace("ii", "&").replace("ia", "+").replace("is", "/").replace("ib", " ").replace("ie", "=").replace("&", "i");
		try {
			result = new String(Base64.getDecoder().decode(target), CHARSET_UTF8);
		} catch (UnsupportedEncodingException e) {
		}
		return result;
	}
	
	public static void main(String[] args) {
		String target = "ABCDEFG中国人有味2##@!#$8716y8fdsafdklskddk大吃大喝";
		System.out.println(encode(target));
		System.out.println(decode(encode(target)));
	}

}