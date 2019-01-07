package com.yc.trip.api.core.util;

import org.go.framework.util.common.StringUtil;
import org.go.framework.util.hash.MD5;

/**
 * 密码工具类
 * 
 * @author dawei.liu
 * @version $Id: PwdUtils.java,v 0.1 2016年10月19日 下午5:47:21 xxx$Exp
 */
public class PwdUtils {

	/**
	 * 对密码进行加密
	 * 
	 * @param operatorId
	 *            操作员ID
	 * @param password
	 *            密码明文
	 * @param salt
	 *            盐值
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String operatorId, String password, String salt) throws Exception {
		StringBuilder text = new StringBuilder();
		text.append(StringUtil.trim(operatorId));
		text.append(StringUtil.trim(password));
		text.append(StringUtil.trim(salt));
		return MD5.Encode16(salt + MD5.Encode16(text.toString()));
	}

	public static void main(String[] args) throws Exception {
		System.out.println(encrypt("18700000000_19_1", "000000", "670b1"));
	}
}
