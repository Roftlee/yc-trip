/**
 * Title: XmlUtils.java<br/>
 * Description: <br/>
 * Copyright: Copyright (c) 2016<br/>
 * 
 *
 */
package com.yc.trip.api.core.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.go.framework.core.exception.AbortException;

import java.io.Writer;

/**
 * Title: XmlUtils<br/>
 * Description: <br/>
 * 
 * 
 * @author youmingwei
 * @date 2016年12月7日上午11:44:12
 *
 */
public class XmlUtils {

	/**
	 * Title: objectToXml <br/>
	 * Description: 对象转换xml <br/>
	 * 1、需要CDATA的属性，加注解：@XmlJavaTypeAdapter(CDataAdapter.class) <br/>
	 * 2、无需序列号的属性，加注解：
	 * 
	 * @param obj
	 * @return
	 * @throws AbortException
	 */
	public static String objectToXml(Object obj) throws AbortException {
		XStream xstream = new XStream(new XppDriver(new NoNameCoder()) {

			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out) {
					// 对所有xml节点的转换都增加CDATA标记
					boolean cdata = true;

					@Override
					@SuppressWarnings("rawtypes")
					public void startNode(String name, Class clazz) {
						super.startNode(name, clazz);
					}

					@Override
					public String encodeNode(String name) {
						return name;
					}

					@Override
					protected void writeText(QuickWriter writer, String text) {
						if (cdata) {
							writer.write("<![CDATA[");
							writer.write(text);
							writer.write("]]>");
						} else {
							writer.write(text);
						}
					}
				};
			}
		});
		xstream.alias("xml", obj.getClass());
		return xstream.toXML(obj);
	}

	/**
	 * Title: objectToXml <br/>
	 * Description: xml转换对象 <br/>
	 * 
	 * @param obj
	 * @return
	 * @throws AbortException
	 */
	public static Object xmlToObject(String xmlStr, Class objClass) throws AbortException {
		XStream xstream = new XStream();
		xstream.alias("xml", objClass);
		return xstream.fromXML(xmlStr);
	}
}
