package com.zkingsoft.actions.admin.bus;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zkingsoft.constraint.BaseController;
import com.zkingsoft.pojo.AjaxResult;
import com.zkingsoft.util.StringUtils;

/**
 * This field was generated by Zking.software.Codegen.
 * 
 * @date 2016-11-28 15:14
 */
@Controller
@RequestMapping(value = "admin/uploadFile")
public class FileUploadController extends BaseController {
	Logger log = Logger.getLogger(FileUploadController.class);

	@Value("${file_storage_path}")
	private String fileStoragePath;
	@Value("${nginx_url}")
	private String nginxUrl;
	// 最大文件大小
	private long maxSize = 1024 * 1024 * 5;
	private String fileTypeImg = "image";
	// 定义文件上传类型
	public static HashMap<String, String> extMap = new HashMap<String, String>();
	static {
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");
	}

	/**
	 * 通用的图上传action 本方法目前只能支持单文件上传，如果需要多文件还要好好想想
	 * 
	 * @author jiangyouyao
	 */
	@RequestMapping(value = "/doUpload")
	public String doFileUpload(HttpServletResponse response, MultipartHttpServletRequest request)
			throws IOException, FileUploadException {
		// 文件保存目录路径
		String savePath = fileStoragePath;
		// 文件保存目录URL
		String saveUrl = nginxUrl;
		String msgPag = "common/fileUploadResult";

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 保存和访问路径检查
		if (StringUtils.isBlank(saveUrl) || StringUtils.isBlank(savePath)) {
			request.setAttribute("status", "err");
			request.setAttribute("msg", "文件上传失败错误代码：001");
			return msgPag;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdir();
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			request.setAttribute("status", "err");
			request.setAttribute("msg", "上传目录没有写权限");
			return msgPag;
		}

		// 创建文件夹
		savePath += fileTypeImg + "/";
		saveUrl += fileTypeImg + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		Map<String, MultipartFile> fileMaps = request.getFileMap();
		for (String key : fileMaps.keySet()) {
			MultipartFile file = fileMaps.get(key);
			log.info("上传文件名：" + file.getOriginalFilename());
			log.info("上传文件大小：" + file.getBytes().length);
			if (file.getBytes().length > maxSize) {
				request.setAttribute("status", "err");
				request.setAttribute("msg", "上传文件大小超过限制");
				return msgPag;
			}
			String fileName = file.getOriginalFilename();
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.<String>asList(extMap.get(fileTypeImg).split(",")).contains(fileExt)) {
				request.setAttribute("status", "err");
				request.setAttribute("msg", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(fileTypeImg) + "格式。");
				return msgPag;
			}

			String newFileName = StringUtils.getUUIDString() + "." + fileExt;
			File uploadedFile = new File(savePath, newFileName);
			try {
				FileCopyUtils.copy(file.getBytes(), uploadedFile);
			} catch (Exception e) {
				request.setAttribute("status", "err");
				request.setAttribute("msg", "上传文件失败 错误代码002");
				return msgPag;
			}
			String visitPath = saveUrl + newFileName;
			log.info("上传一个文件：" + newFileName);
			// 获取回调函数
			String callBack = request.getParameter("callBack");
			String inputId = request.getParameter("inputId");
			request.setAttribute("status", "ok");
			request.setAttribute("callBack", callBack);
			request.setAttribute("inputId", inputId);
			request.setAttribute("url", visitPath);
		}

		return msgPag;
	}

	/**
	 * 图片转换base64-png
	 */
	@RequestMapping(value = "/base64Image2Png")
	public @ResponseBody AjaxResult base64Image2Png(String base64Image) {
		// 判断文件是否存在
		String url = null;
		log.info(url);
		AjaxResult result = new AjaxResult(AjaxResult.STATUS_OK, null, url);
		return result;
	}

}