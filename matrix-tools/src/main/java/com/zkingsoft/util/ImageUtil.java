package com.zkingsoft.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import com.zkingsoft.util.uploadFile.UploadUtils;

import sun.misc.BASE64Decoder;

/**
 * 
 * @description 照片工具类
 * @author Matrix-J
 * @data 2015年8月6日 下午7:16:18
 */
public class ImageUtil {
	/**
	 * 把图片文件压缩为JPG文件
	 * 
	 * @description
	 * @data 2015年8月6日 下午7:17:59
	 * @author Administrator ·* @author JYY
	 * @param file
	 *            源文件
	 * @param newHeight
	 *            新宽度
	 * @param newWidth
	 *            新高度
	 * @param outputDir
	 *            输出目录
	 * @param outputFileName
	 *            输出文件名称
	 * @throws IOException 
	 */
	public static void compressionImg(File file, int newHeight, int newWidth, String outputDir, String outputFileName) throws IOException {

			Image img = ImageIO.read(file);
			// // 判断图片格式是否正确
			BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			/*
			 * Image.SCALE_SMOOTH 的缩略算生成缩略图片的平滑度优先级比速度生成的图片质量比较好 但度慢
			 */
			tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
			FileOutputStream out = new FileOutputStream(outputDir + outputFileName);
			// JPEGImageEncoder可用于其他图片类型的转
			ImageIO.write(tag, "jpg", out);
			out.close();
	}

	public String getFileByteString(File file) throws Exception {
		Base64 b64 = new Base64();
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		int count = fis.read(buffer);
		fis.close();
		if (count > 0) {
			return b64.encodeToString(buffer);
		} else {
			return "";
		}

	}

	public String getFileByString(String imgStr, String savePath, String saveUrl) throws Exception {
		System.out.println(imgStr);
		Base64 b64 = new Base64();
		byte[] buffer = b64.decode(imgStr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + ".jpg";
		File uploadedFile = new File(savePath, newFileName);
		FileOutputStream fos = new FileOutputStream(uploadedFile);
		fos.write(buffer);
		fos.close();
		String visitPath = saveUrl + newFileName;
		return visitPath;
	}

	/**
	 * base64转换成Image
	 * 
	 * @param imgSrc
	 * @param url
	 * @return
	 */
	public static String base64Image2Png(String base64Image, String savePath, String saveUrl) {
		if (base64Image != null && base64Image != "") {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				UploadUtils uploadUtils = new UploadUtils();
				// 文件保存路径
				String path = uploadUtils.createFolder(savePath, saveUrl, "image");
				String url = path + new Date().getTime() + ".png";
				System.out.println(url);
				// 解密
				byte[] b = decoder.decodeBuffer(base64Image.replace("data:image/png;base64,", ""));
				// 处理数据
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {
						b[i] += 256;
					}
				}
				OutputStream out = new FileOutputStream(url);
				out.write(b);
				out.flush();
				out.close();
				System.out.println(url.replace(savePath, saveUrl));
				return url.replace(savePath, saveUrl);
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}

	}

	public static void main(String[] args) throws Exception {
		ImageUtil imageUtil = new ImageUtil();
		File file = new File("C:/Users/Administrator/Desktop/2/consent.png");
		imageUtil.getFileByString(imageUtil.getFileByteString(file), "C:/Users/Administrator/Desktop/", null);
	}

}