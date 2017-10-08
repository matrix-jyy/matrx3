package com.zkingsoft.util.uploadFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zkingsoft.util.StringUtils;

/**
 * 上传文件工具类
 * @Title: UploadUtils.java  
 * @Package com.zkingsoft.actions.common.upload  
 * @description 
 * @author 罗凯
 * @email 18075895212@qq.com
 * @date  2016年11月13日 上午9:55:47
 */
public class UploadUtils {
	//存储路径
	private  String fileStoragePath;
	//nginxUrl访问路径
	private  String nginxUrl;
	//定义文件上传的类型及限制类型
	public static HashMap<String, String> extMap = new HashMap<String, String>();
	static{
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf");
	}
	//定义最大文件大小
	private long maxSize =1024* 1024*5;
	
	
	public UploadUtils(String fileStoragePath,String nginxUrl) {
		this.fileStoragePath=fileStoragePath;
		this.nginxUrl=nginxUrl;
	}  
	public UploadUtils(){}
	

	public  UploadResult uploadFile(MultipartHttpServletRequest request) throws IOException, FileUploadException{
		UploadResult result=new UploadResult();
		result.setStatus(UploadResult.STATUS_ERR);
		if(StringUtils.isBlank(fileStoragePath)||StringUtils.isBlank(nginxUrl))
		{
			result.setInfo("路径错误,请联系管理处理!");
			return result;
		}
		String savePath=new String(fileStoragePath);
		String saveUrl=new String(nginxUrl);
		
		if(!ServletFileUpload.isMultipartContent(request)){
			result.setInfo("请选择文件。");
			return result;
		}
		//检查目录
		String fileType = request.getParameter("fileType");
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			uploadDir.mkdir();
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			result.setInfo("上传目录没有写权限。");
			return result;
		}

		if(!extMap.containsKey(fileType)){
			result.setInfo("不允许上传该文件类型!");
			return result;
		}
		
		//创建文件夹
		createFolder(savePath,saveUrl,fileType);
		
		/*	savePath += fileType + "/";
			saveUrl += fileType + "/";
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
			}*/
			
			DiskFileItemFactory factory = new DiskFileItemFactory();  
			//最大缓存  
			factory.setSizeThreshold(5*1024);  
			//设置临时文件目录  
			factory.setRepository(new File(savePath));  
			ServletFileUpload upload = new ServletFileUpload(factory);  
			    //文件最大上限  
		    upload.setSizeMax(maxSize);  
			  
		    Map<String, MultipartFile> fileMaps =request.getFileMap();
		    for(String key : fileMaps.keySet()) {  
		    	MultipartFile file = fileMaps.get(key);  
		    	System.out.println("上传文件名："+file.getOriginalFilename());
		    	System.out.println("上传文件大小："+file.getBytes().length);
		    	if(file.getBytes().length > maxSize){
					result.setInfo("上传文件大小超过限制。");
					return result;
				}
		    	String fileName = file.getOriginalFilename();
		    	String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		    	if(!Arrays.<String>asList(extMap.get(fileType).split(",")).contains(fileExt)){
					result.setInfo("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(fileType) + "格式。");
					return result;
				}
		    	fileName=fileName.replace("."+fileExt, "");
		    	String newFileName = fileName + "_" + new Random().nextInt(1000) + "." + fileExt;
		    	File uploadedFile = new File(savePath, newFileName);
		    	try {
		    		FileCopyUtils.copy(file.getBytes(), uploadedFile);
				} catch (Exception e) {
					// TODO: handle exception
					result.setInfo("上传失败");
					return result;
				}
		    	result.setInfo("上传成功!");
		    	result.setUrl(saveUrl+newFileName);
		    	result.setStatus(UploadResult.STATUS_OK);
		    }  
		    return result;
	}
	public static HashMap<String, String> getExtMap() {
		return extMap;
	}
	public static void setExtMap(HashMap<String, String> extMap) {
		UploadUtils.extMap = extMap;
	}



	public long getMaxSize() {
		return maxSize;
	}



	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}
	
	//判断文件夹是否存在--不存在则创建文件夹
	public String createFolder (String savePath,String saveUrl,String fileType){
		savePath += fileType + "/";
		saveUrl += fileType + "/";
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
		return savePath;
	}
	
	
}
			      
		
			
