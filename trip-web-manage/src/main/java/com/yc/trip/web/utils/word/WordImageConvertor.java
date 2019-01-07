package com.yc.trip.web.utils.word;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;


/**
 * @version V1.0
 * @Description:WORD 文档图片转换器
 * @date :2016-3-28 上午11:21:06
 */
public class WordImageConvertor {

    /**
     * @param @param  imageSrc 文件路径
     * @param @return
     * @return String
     * @throws IOException
     * @throws
     * @Description: 将图片转换成base64编码的字符串
     * @author:LiaoFei
     * @date:2016-3-28 上午11:22:26
     */
    public static String imageToBase64(String imageSrc) throws IOException {
        //判断文件是否存在
        File file = new File(imageSrc);
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在！");
        }
        StringBuilder pictureBuffer = new StringBuilder();
        FileInputStream input = new FileInputStream(file);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //读取文件

        Base64 base64 = new Base64();
        byte[] temp = new byte[1024];
        for (int len = input.read(temp); len != -1; len = input.read(temp)) {
            out.write(temp, 0, len);
        }
        pictureBuffer.append(new String(base64.encodeBase64Chunked(out.toByteArray())));

        input.close();

        return pictureBuffer.toString();
    }

    /**
     *
     * @param imageSrc
     * @return
     * @throws Exception
     */
    public static String imageToBase64URL(String imageSrc) throws Exception {
        if (imageSrc.toLowerCase().startsWith("https")){
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(HttpUtils.doGet(imageSrc));
        }else{
            BASE64Encoder encoder = new BASE64Encoder();

            URL url = new URL(imageSrc);
            InputStream inputStream = url.openStream();
            return encoder.encode(getByte(inputStream));
        }
    }


    public static String toDocBodyBlock(
            String imageFilePath,
            String imageFielShortName,
            int imageHeight,
            int imageWidth,
            String imageStyle,
            String srcLocationShortName,
            String shapeidPrex, String spidPrex, String typeid) {
        //shapeid
        //mht文件中针对shapeid的生成好像规律，其内置的生成函数没法得知，但是只要保证其唯一就行
        //这里用前置加32位的uuid来保证其唯一性。
        String shapeid = shapeidPrex;
        shapeid += UUID.randomUUID().toString();

        //spid ,同shapeid处理
        String spid = spidPrex;
        spid += UUID.randomUUID().toString();
		
		
	/*	<!--[if gte vml 1]><v:shape id=3D"_x56fe__x7247__x0020_0" o:spid=3D"_x0000_i10=
				26"
				   type=3D"#_x0000_t75" alt=3D"725017921264249223.jpg" style=3D'width:456.7=
				5pt;
				   height:340.5pt;visibility:visible;mso-wrap-style:square'>
				   <v:imagedata src=3D"file9462.files/image001.jpg" o:title=3D"725017921264=
				249223"/>
				  </v:shape><![endif]--><![if !vml]><img width=3D609 height=3D454
				  src=3D"file9462.files/image002.jpg" alt=3D725017921264249223.jpg v:shapes=
				=3D"_x56fe__x7247__x0020_0"><![endif]>*/
        StringBuilder sb1 = new StringBuilder();

        sb1.append(" <!--[if gte vml 1]>");
        sb1.append("<v:shape id=3D\"" + shapeid + "\"");
        sb1.append("\n");
        sb1.append(" o:spid=3D\"" + spid + "\"");
        sb1.append(" type=3D\"" + typeid + "\" alt=3D\"" + imageFielShortName + "\"");
        sb1.append("\n");
        sb1.append(" style=3D' " + generateImageBodyBlockStyleAttr(imageFilePath, imageHeight, imageWidth) + imageStyle + "'");
        sb1.append(">");
        sb1.append("\n");
        sb1.append(" <v:imagedata src=3D\"" + srcLocationShortName + "\"");
        sb1.append("\n");
        sb1.append(" o:title=3D\"" + imageFielShortName.split("\\.")[0] + "\"");
        sb1.append("/>");
        sb1.append("</v:shape>");
        sb1.append("<![endif]-->");

        return sb1.toString();
    }

    /**
     * @param @param  nextPartId
     * @param @param  contextLoacation
     * @param @param  ContentType
     * @param @param  base64Content
     * @param @return
     * @return String
     * @throws
     * @Description: 生成图片的base4块
     * @author:LiaoFei
     * @date:2016-3-28 下午4:02:05
     */
    public static String generateImageBase64Block(String nextPartId, String contextLoacation,
                                                  String fileTypeName, String base64Content) {
		/*--=_NextPart_01D188DB.E436D870
				Content-Location: file:///C:/70ED9946/file9462.files/image001.jpg
				Content-Transfer-Encoding: base64
				Content-Type: image/jpeg
				
				base64Content
		*/

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("\n");
        sb.append("------=_NextPart_" + nextPartId);
        sb.append("\n");
        sb.append("Content-Location: " + contextLoacation);
        sb.append("\n");
        sb.append("Content-Transfer-Encoding: base64");
        sb.append("\n");
        sb.append("Content-Type: " + getImageContentType(fileTypeName));
        sb.append("\n");
        sb.append("\n");
        sb.append(base64Content);

        return sb.toString();
    }


    private static String generateImageBodyBlockStyleAttr(String imageFilePath, int height, int width) {
        StringBuilder sb = new StringBuilder();

        BufferedImage sourceImg;
        try {
            if (imageFilePath.startsWith("https://") || imageFilePath.startsWith("http://")) {
                URL url = new URL(imageFilePath);
                URLConnection conn = url.openConnection();
                sourceImg = ImageIO.read(conn.getInputStream());
            } else {
                sourceImg = ImageIO.read(new FileInputStream(imageFilePath));
            }
            if (height == 0) {
                height = sourceImg.getHeight();
            }
            if (width == 0) {
                width = sourceImg.getWidth();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //将像素转化成pt
        BigDecimal heightValue = new BigDecimal(height * 12 / 16);
        heightValue = heightValue.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal widthValue = new BigDecimal(width * 12 / 16);
        widthValue = widthValue.setScale(2, BigDecimal.ROUND_HALF_UP);

        sb.append("height:" + heightValue + "pt;");
        sb.append("width:" + widthValue + "pt;");
        sb.append("visibility:visible;");
        sb.append("mso-wrap-style:square; ");


        return sb.toString();
    }

    private static String getImageContentType(String fileTypeName) {
        String result = "image/jpeg";

        if (fileTypeName.equals("tif") || fileTypeName.equals("tiff")) {
            result = "image/tiff";
        } else if (fileTypeName.equals("fax")) {
            result = "image/fax";
        } else if (fileTypeName.equals("gif")) {
            result = "image/gif";
        } else if (fileTypeName.equals("ico")) {
            result = "image/x-icon";
        } else if (fileTypeName.equals("jfif") || fileTypeName.equals("jpe")
                || fileTypeName.equals("jpeg") || fileTypeName.equals("jpg")) {
            result = "image/jpeg";
        } else if (fileTypeName.equals("net")) {
            result = "image/pnetvue";
        } else if (fileTypeName.equals("png") || fileTypeName.equals("bmp")) {
            result = "image/png";
        } else if (fileTypeName.equals("rp")) {
            result = "image/vnd.rn-realpix";
        } else if (fileTypeName.equals("rp")) {
            result = "image/vnd.rn-realpix";
        }

        return result;
    }


    public static String getFileSuffix(String srcRealPath) {
        String suffix = srcRealPath.substring(srcRealPath.indexOf(".") + 1);
        return suffix;
    }

    public static byte[] getByte(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte[] buf=new byte[1024]; //缓存数组
            while(in.read(buf)!=-1){ //读取输入流中的数据放入缓存，如果读取完则循环条件为false;
                out.write(buf); //将缓存数组中的数据写入out输出流，如果需要写到文件，使用输出流的其他方法
            }
            out.flush();
            return out.toByteArray();	//将输出流的结果转换为字节数组的形式返回	（先执行finally再执行return	）
        } finally{
            if(in!=null){
                in.close();
            }
            if(out!=null){
                out.close();
            }
        }
    }
}
