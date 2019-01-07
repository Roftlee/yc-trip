/**
 * 
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.yc.trip.api.core.util;

import org.go.framework.core.exception.AbortException;

/**
 * byte辅助类
 *
 * @author chenbo
 * @version : ByteUtil.java.java,v 0.1 2016年1月27日 chenbo
 */
public class ByteUtil {

	private static final int two = 2;

	public static byte[] contactArray(byte[] src1, byte[] src2) throws AbortException {
		if (src1 == null || src1 == null) {
			throw new AbortException(null, "contactArray,数据编码失败");
		}
		byte[] dest = new byte[src1.length + src2.length];
		System.arraycopy(src1, 0, dest, 0, src1.length);
		System.arraycopy(src2, 0, dest, src1.length, src2.length);
		return dest;
	}

	public static byte[] subArray(byte[] src, int start, int end) throws AbortException {
		if (start < 0 || start > end || end > src.length) {
			throw new AbortException(null, "subArray,数据编码失败");
		}
		byte[] subBytes = new byte[end - start];
		System.arraycopy(src, start, subBytes, 0, subBytes.length);
		return subBytes;
	}

	public static String arrayHexString(byte[] src, String delim) {
		if (delim == null) {
			delim = "";
		}
		if (src == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < src.length; i++) {
			byte byteNumber = src[i];
			sb.append(hexString(byteNumber));
			sb.append(delim);
		}
		String toPrint = sb.toString();
		int start = toPrint.length() - delim.length();
		if (delim.equals(toPrint.substring(start, toPrint.length()))) {
			toPrint = toPrint.substring(0, start);
		}
		return toPrint;
	}

	public static String arrayShortHexString(byte[] src, String delim) {
		return arrayHexString(src, delim).replace("0x", "");
	}

	public static String arrayShortHexString(byte[] src) {
		return arrayHexString(src, null).replace("0x", "");
	}

	private static String hexString(byte byteNumber) {
		int toStr = byteNumber;
		if (byteNumber < 0) {
			toStr = byteNumber + 256;
		}
		String byteStr = Integer.toHexString(toStr);
		if (byteStr.length() == 1) {
			byteStr = "0" + byteStr;
		}
		return "0x" + byteStr.toUpperCase();
	}

	public static byte[] hexStringToBytes(String hexStr) throws AbortException {
		int length = hexStr.length();
		if (length % two != 0) {
			throw new AbortException(null, hexStr
					+ "不是16进制数");
		}
		hexStr = hexStr.toUpperCase();
		byte[] outArray = new byte[length / two];
		for (int i = 0; i < length; i += two) {
			char li = hexStr.charAt(i);
			char lo = hexStr.charAt(i + 1);
			if (li < '0' || li > 'F' || lo < '0' || lo > 'F') {
				throw new AbortException(null, null);
			}
			outArray[i / two] = (byte) Integer.parseInt(
					String.valueOf(new char[] { li, lo }), 16);
		}
		return outArray;
	}

	public static byte[] asciiToBcd(String input) throws AbortException {
		byte[] ascii = null, bcd = null;
		try {
			ascii = input.getBytes("US-ASCII");
			if (ascii.length % two != 0) {
				throw new IllegalArgumentException();
			}
			bcd = new byte[ascii.length / two];
			for (int i = 0; i < ascii.length; i += two) {
				if (ascii[i] < 0x30 || ascii[i] > 0x39) {
					throw new IllegalArgumentException();
				}
				int temp = (ascii[i] & 0x0F) << 4;
				if (temp > 127) {
					temp -= 256;
				}
				byte hi = (byte) temp;
				byte lo = (byte) (ascii[i + 1] & 0x0F);
				bcd[i / 2] = (byte) (hi | lo);
			}
		} catch (Exception e) {
			throw new AbortException(null,
					"asciiToBcd异常", e);
		}
		return bcd;
	}

	public static String bcdToAscii(byte[] bcd) {
		byte[] ascii = new byte[two * bcd.length];
		for (int i = 0; i < bcd.length; i++) {
			byte hi = (byte) (bcd[i] >> 4);
			byte lo = (byte) (bcd[i] & 0x0F);
			if (hi < 0x00 || hi > 0x09 || lo < 0x00 || lo > 0x09) {
				throw new IllegalArgumentException();
			}
			ascii[2 * i] = ((byte) (hi + 0x30));
			ascii[2 * i + 1] = ((byte) (lo + 0x30));
		}
		return new String(ascii);
	}

	public static String toHexString(byte[] block) {
		return arrayShortHexString(block);
	}

	public static byte[] intToHexBytes(int srcInt, int byteCount)
			throws AbortException {
		if (byteCount < 1 || byteCount > 4) {
			throw new AbortException(null,
					"intToHexBytes异常");
		}
		int max = (int) Math.pow(two, 8 * byteCount) - 1;
		if (srcInt < 0 || srcInt > max) {
			throw new AbortException(null,
					"intToHexBytes异常");
		}
		String hexStr = Integer.toHexString(srcInt);
		if (hexStr.length() % two != 0) {
			hexStr = "0" + hexStr;
		}
		int paddingByteCount = byteCount - hexStr.length() / 2;
		if (paddingByteCount < 0) {
			throw new AbortException(null,
					"srcInt is larger than " + max);
		}
		byte[] paddingBytes = new byte[paddingByteCount];
		for (int i = 0; i < paddingBytes.length; i++) {
			paddingBytes[i] = 0x00;
		}
		byte[] unpaddingBytes = new byte[hexStr.length() / 2];
		for (int i = 0; i < unpaddingBytes.length; i++) {
			String byteHex = hexStr.substring(2 * i, 2 * i + 2);
			unpaddingBytes[i] = (byte) Integer.parseInt(byteHex, 16);
		}
		return contactArray(paddingBytes, unpaddingBytes);
	}

	public static int binaryToInt(byte[] binary) {
		String hexStr = toHexString(binary);
		return Integer.parseInt(hexStr, 16);
	}

	public static long binaryToLong(byte[] binary) {
		String hexStr = toHexString(binary);
		return Long.parseLong(hexStr, 16);
	}

	public static byte[] longToHexBytes(long srcLong, int byteCount)
			throws AbortException {
		if (byteCount < 1 || byteCount > 8) {
			throw new AbortException(null,
					"byteCount should not be less than 1 or larger than 4");
		}
		long max = (long) Math.pow(two, 8 * byteCount) - 1;
		if (srcLong < 0 || srcLong > max) {
			throw new AbortException(null,
					"srcLong should not be less than 0 or larger than " + max);
		}
		String hexStr = Long.toHexString(srcLong);
		if (hexStr.length() % two != 0) {
			hexStr = "0" + hexStr;
		}
		int paddingByteCount = byteCount - hexStr.length() / two;
		if (paddingByteCount < 0) {
			throw new AbortException(null,
					"srcLong is larger than " + max);
		}
		byte[] paddingBytes = new byte[paddingByteCount];
		for (int i = 0; i < paddingBytes.length; i++) {
			paddingBytes[i] = 0x00;
		}
		byte[] unpaddingBytes = new byte[hexStr.length() / two];
		for (int i = 0; i < unpaddingBytes.length; i++) {
			String byteHex = hexStr.substring(two * i, two * i + two);
			unpaddingBytes[i] = (byte) Integer.parseInt(byteHex, 16);
		}
		return contactArray(paddingBytes, unpaddingBytes);
	}

	/**
	 * 
	 * toHexString 字符串转16进制字符串
	 * 
	 * @param ByteUtil
	 * @return String
	 */
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/**
	 * 
	 * toStringHex 16进制字符串转字符串
	 * 
	 * @param ByteUtil
	 * @return String
	 * @throws AbortException
	 */
	public static String toStringHex(String s) throws AbortException {
		byte[] baKeyword = new byte[s.length() / two];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * two, i * two + two), 16));
			} catch (Exception e) {
				throw new AbortException(null,
						"转换16进制字符串异常");
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			throw new AbortException(null,
					"转换16进制字符串异常");
		}
		return s;
	}
	
	public static String bcd2String(byte [] byt){
		String src = "";
		for(int i = 0;i<byt.length ;i++){
			src=src+byt[i];
		}
		return src;
	}

}
