package com.yc.trip.api.core.util;
///**
// * Title: WeiXinMD5Util.java<br/>
// * Description: <br/>
// * Copyright: Copyright (c) 2016<br/>
// * 
// *
// */
//package com.yc.clouds.api.core.util;
//
//imp java.beans.PropertyDescriptor;
//imp java.io.UnsupportedEncodingException;
//imp java.util.Iterator;
//imp java.util.Map;
//imp java.util.Set;
//imp java.util.SortedMap;
//imp java.util.TreeMap;
//
//imp org.apache.commons.beanutils.PropertyUtilsBean;
//imp org.slf4j.Logger;
//imp org.slf4j.LoggerFactory;
//
//imp org.go.framework.core.exception.AbortException;
//imp org.go.framework.util.hash.MD5;
//
///**
// * Title: 微信MD5签名算法<br/>
// * Description: <br/>
// * 
// * 
// * @author youmingwei
// * @date 2016年12月7日下午2:29:38
// *
// */
//public class WeiXinMD5Util {
//	private final static Logger logger = LoggerFactory.getLogger(BeanMapping.class);
//
//	/**
//	 * 
//	 * Title: 微信签名算法<br/>
//	 * Description:
//	 * 第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（
//	 * 即key1=value1&key2=value2…）拼接成字符串stringA。<br/>
//	 * 特别注意以下重要规则： <br/>
//	 * ◆ 参数名ASCII码从小到大排序（字典序）；<br/>
//	 * ◆ 如果参数的值为空不参与签名； <br/>
//	 * ◆ 参数名区分大小写； <br/>
//	 * ◆ 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。<br/>
//	 * ◆ 微信接口可能增加字段，验证签名时必须支持增加的扩展字段
//	 * 第二步，在stringA最后拼接上key=(API密钥的值)得到stringSignTemp字符串，并对stringSignTemp进行MD5运算
//	 * ，再将得到的字符串所有字符转换为大写，得到sign值signValue <br/>
//	 * 
//	 * @author youmingwei
//	 * @date 2016年12月7日下午2:43:17
//	 *
//	 * @param 加密对象
//	 * @param 编码
//	 * @param 盐值
//	 * @return
//	 * @throws AbortException
//	 */
//	public static String createSign(Object obj, String characterEncoding, String key) throws AbortException {
//		try {
//			SortedMap<String, Object> sortMap = beanToSortedMap(obj);
//			StringBuffer sb = new StringBuffer();
//			Set es = sortMap.entrySet();// 所有参与传参的参数按照accsii排序（升序）
//			Iterator it = es.iterator();
//			while (it.hasNext()) {
//				Map.Entry entry = (Map.Entry) it.next();
//				String k = (String) entry.getKey();
//				Object v = entry.getValue();
//				if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
//					sb.append(k + "=" + v + "&");// sign和空值不参与加密
//				}
//			}
//			sb.append("key=" + key);// 加盐值
//			String sign = MD5.Encode16(sb.toString().getBytes(characterEncoding)).toUpperCase();
//			return sign;
//		} catch (UnsupportedEncodingException e) {
//			throw new AbortException("", "生成MD5串出错了", e);
//		} catch (Exception e) {
//			throw new AbortException("", "生成MD5串出错了", e);
//		}
//	}
//
//	public static SortedMap<String, Object> beanToSortedMap(Object obj) throws AbortException {
//		SortedMap<String, Object> sortMap = new TreeMap<String, Object>();
//		try {
//			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
//			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
//			for (int i = 0; i < descriptors.length; i++) {
//				String name = descriptors[i].getName();
//				if (!"class".equals(name)) {
//					sortMap.put(name, propertyUtilsBean.getNestedProperty(obj, name));
//				}
//			}
//		} catch (Exception e) {
//			throw new AbortException("", "beanToSortedMap出错了", e);
//		}
//		return sortMap;
//	}
//
//}
