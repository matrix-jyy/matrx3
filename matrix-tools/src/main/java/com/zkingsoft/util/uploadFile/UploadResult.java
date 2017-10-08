package com.zkingsoft.util.uploadFile;

public class UploadResult {
	public final static String STATUS_OK="ok";
	public final static String STATUS_ERR="err";
	
	
	/**
	 * 上传 是否成功/失败 
	 */
	public String status;
	/**
	 * 保存路径
	 */
	public String savePath;
	/**
	 * 访问路径
	 */
	public String url;
	/**
	 * 返回信息
	 */
	public String info;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "UploadResult [status=" + status + ", savePath=" + savePath
				+ ", url=" + url + ", info=" + info + "]";
	}
}
