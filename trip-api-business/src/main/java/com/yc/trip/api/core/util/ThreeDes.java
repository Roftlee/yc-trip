/**
 * 
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.yc.trip.api.core.util;

import org.go.framework.core.exception.AbortException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * 3DES辅助类
 * 
 * @author chenbo
 * @version $Id: ThreeDes.java,v 0.1 2016年1月29日 上午10:21:50 chenbo$Exp
 */

public class ThreeDes {

	/**
	 * DES,DESede,Blowfish 定义加密算法,可用
	 */
	private static final String Algorithm = "DESede";

	/**
	 * 填充方式
	 */
	private static final String deAlgorithm = "DESede/ECB/NoPadding";

	/**
	 * 
	 * encryptMode 加密
	 * 
	 * @param ThreeDes
	 * @return byte[]
	 * @throws AbortException
	 */
	public static byte[] encryptMode(byte[] keybyte, byte[] src)
			throws AbortException {
		try {
			src = buildBodyBytes(src);
			// 填充Key
			keybyte = build3DesKey(keybyte);
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, 0, 24, Algorithm);

			// 加密
			Cipher c1 = Cipher.getInstance(deAlgorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);// 在单一方面的加密或解密
		} catch (Exception e3) {
			throw new AbortException("", "加密出错了", e3);
		}
	}

	/**
	 * 
	 * decryptMode 解密
	 * 
	 * @param ThreeDes
	 * @return byte[]
	 * @throws AbortException
	 */
	public static byte[] decryptMode(byte[] keybyte, byte[] src)
			throws AbortException {
		byte[] busDt = null;
		try {
			// 填充Key
			keybyte = build3DesKey(keybyte);
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, 0, 24, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(deAlgorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			busDt = c1.doFinal(src);
		} catch (Exception e3) {
			throw new AbortException("",
					"POS数据解密失败", e3);
		}
		return busDt;
	}

	public static byte[] build3DesKey(byte[] temp)

	throws UnsupportedEncodingException {

		byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0

		System.arraycopy(temp, 0, key, 0, temp.length);

		// 补充的8字节就是16字节密钥的前8位

		for (int i = 0; i < 8; i++) {

			key[16 + i] = temp[i];

		}
		return key;
	}

	public static byte[] buildBodyBytes(byte[] body) throws AbortException {
		int len = body.length % 8 == 0 ? 0 : 8 - (body.length % 8);
		if (len > 0) {
			byte[] addByte = new byte[len];
			try {
				for (int i = 0; i < addByte.length; i++) {
					addByte[i] = 0x00;
				}
				body = ByteUtil.contactArray(body, addByte);
			} catch (Exception ex) {
				throw new AbortException("",
						"POS数据补充", ex);
			}
		}
		return body;
	}
	
	public static void main(String[] args) throws AbortException {
		String key = "1234567890123456";
		String content = "12345600";
		byte []password = encryptMode(key.getBytes(), content.getBytes());
		byte[]pass = decryptMode(key.getBytes(), password);
		System.out.println(pass.length);
		System.out.println(new String(pass));
	}

}
