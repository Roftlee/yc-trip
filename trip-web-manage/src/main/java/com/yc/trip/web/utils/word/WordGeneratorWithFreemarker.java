package com.yc.trip.web.utils.word;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.go.framework.util.common.StringUtil;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Description:word导出帮助类 通过freemarker模板引擎来实现
 * @author:LiaoFei
 * @date :2016-3-24 下午3:49:25
 */
public class WordGeneratorWithFreemarker {

    private static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassicCompatible(true);
        URL filePath = WordGeneratorWithFreemarker.class.getResource("/");
        try {
            configuration.setDirectoryForTemplateLoading(new File(filePath.getPath() + "ftl/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private WordGeneratorWithFreemarker() {

    }

    public static void createDoc(Map<String, Object> dataMap, String templateName, OutputStream out) throws Exception {
        Template t = configuration.getTemplate(templateName);
        t.setEncoding("utf-8");
        WordHtmlGeneratorHelper.handleAllObject(dataMap);

        try {
            Writer w = new OutputStreamWriter(out, Charset.forName("utf-8"));
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * freemarker模板文件生成html
     *
     * @param fileName
     * @param globalMap
     * @return
     */
    public String loadFtlHtml(String fileName, Map<?, ?> globalMap) {
        if (globalMap == null || StringUtil.isBlank(fileName)) {
            throw new IllegalArgumentException("生成Html参数错误");
        }
        try {
            Template temp = configuration.getTemplate(fileName);
            StringWriter stringWriter = new StringWriter();
            temp.process(globalMap, stringWriter);
            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成试卷的word文件,返回文件名和路径
     *
     * @param paperHtml
     * @param path
     * @return
     * @throws Exception
     */
    public static String generatorWord(String paperHtml, String path, String paperTitle) throws Exception {

        HashMap<String, Object> data = new HashMap<String, Object>();

        RichHtmlHandler handler = new RichHtmlHandler(paperHtml);
        handler.setDocSrcLocationPrex("file:///C:/D1324D12");
        handler.setDocSrcParent("paper.files");
        handler.setNextPartId("01D48324.7B8852E0");
        handler.setShapeidPrex("_x56fe__x7247__x0020");
        handler.setSpidPrex("_x0000_i");
        handler.setTypeid("#_x0000_t75");
        handler.handledHtml(false);

        String bodyBlock = handler.getHandledDocBodyBlock();

        String handledBase64Block = "";
        if (handler.getDocBase64BlockResults() != null
                && handler.getDocBase64BlockResults().size() > 0) {
            for (String item : handler.getDocBase64BlockResults()) {
                handledBase64Block += item + "\n";
            }
        }
        data.put("imagesBase64String", handledBase64Block);

        String xmlimaHref = "";
        if (handler.getXmlImgRefs() != null
                && handler.getXmlImgRefs().size() > 0) {
            for (String item : handler.getXmlImgRefs()) {
                xmlimaHref += item + "\n";
            }
        }
        data.put("imagesXmlHrefString", xmlimaHref);
        data.put("content", bodyBlock);
        data.put("title", paperTitle);

        String docFilePath = path + paperTitle + ".doc";

        File f = new File(docFilePath);
        OutputStream out;
        try {
            out = new FileOutputStream(f);
            WordGeneratorWithFreemarker.createDoc(data, "paper.ftl", out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return docFilePath;
    }
}