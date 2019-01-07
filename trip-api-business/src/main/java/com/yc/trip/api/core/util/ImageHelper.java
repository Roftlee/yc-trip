package com.yc.trip.api.core.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * 图片加工处理
 * 
 * @author xiebin
 * @since 2016-10-17
 */
public class ImageHelper {

	/**** start 基于流 ****/

	/**
	 * 根据尺寸图片居中裁剪 裁出正方形
	 * 
	 * @param in
	 * @param tempFile
	 * @param imgTye
	 * @throws IOException
	 */
	public static void cutCenterImageForStream(InputStream in, File tempFile, String imgTye) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName(imgTye);
		ImageReader reader = (ImageReader) iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		int w = reader.getWidth(0);
		int h = reader.getHeight(0);
		int size = w > h ? h : w;
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - size) / 2,
				(reader.getHeight(imageIndex) - size) / 2, size, size);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, imgTye, tempFile);

	}
	

	/**
	 * 根据尺寸图片居中裁剪 指定大小 矩形
	 * 
	 * @param in
	 * @param tempFile
	 * @param imgTye
	 * @param w
	 * @param h
	 * @throws IOException
	 */
	public static void cutCenterImageForStream(InputStream in, File tempFile, String imgTye, int w, int h)
			throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName(imgTye);
		ImageReader reader = (ImageReader) iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w,h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, imgTye, tempFile);

	}

	/**
	 * 图片裁剪通用接口
	 * 
	 * @param in
	 *            文件流
	 * @param tempFile
	 *            临时文件
	 * @param x
	 * @param y
	 *            裁剪的开始位置
	 * @param w
	 * @param h
	 *            裁剪的大小
	 * @throws IOException
	 */
	public static void cutImageForStream(InputStream in, File tempFile, String imgTye, int x, int y, int w, int h)
			throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName(imgTye);
		ImageReader reader = (ImageReader) iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, imgTye, tempFile);
	}

	/**** end 基于流 ****/

	/**** start 基于文件 ****/

	/*
	 * 根据尺寸图片居中裁剪
	 */
	public static void cutCenterImage(String src, String dest, int w, int h) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w,
				h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", new File(dest));

	}

	/*
	 * 图片裁剪通用接口
	 */

	public static void cutImageForStream(InputStream in, File tempFile, int x, int y, int w, int h) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = (ImageReader) iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", tempFile);

	}

	/*
	 * 图片裁剪二分之一
	 */
	public static void cutHalfImage(String src, String dest) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		int width = reader.getWidth(imageIndex) / 2;
		int height = reader.getHeight(imageIndex) / 2;
		Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", new File(dest));
	}
	/*
	 * 图片裁剪通用接口
	 */

	public static void cutImage(String src, String dest, int x, int y, int w, int h) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", new File(dest));

	}

	/*
	 * 图片缩放
	 */
	public static void zoomImage(String src, String dest, int w, int h) throws Exception {
		double wr = 0, hr = 0;
		File srcFile = new File(src);
		File destFile = new File(dest);
		BufferedImage bufImg = ImageIO.read(srcFile);
		Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
		wr = w * 1.0 / bufImg.getWidth();
		hr = h * 1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static File createTempFile(String prefix, String suffix) {
		File temp = null;
		try {
			temp = File.createTempFile(prefix, suffix);
			temp.deleteOnExit();
		} catch (IOException e) {
		}
		return temp;

	}

}
