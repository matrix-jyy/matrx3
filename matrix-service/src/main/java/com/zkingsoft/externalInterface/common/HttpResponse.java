package com.zkingsoft.externalInterface.common;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class HttpResponse {
	private InputStream inputStream;
	private String fileName;
	private String contentType;
	private int contentLength;
	Logger log=Logger.getLogger(HttpResponse.class);
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return this.inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getContentLength() {
		return this.contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getDataString() {
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader isr = new InputStreamReader(getInputStream(), "utf-8");
			char[] cbuf = new char[1024];
			int hasRead = 0;
			while ((hasRead = isr.read(cbuf)) > 0) {
				sb.append(cbuf, 0, hasRead);
			}
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("request data=" + sb.toString());
		return sb.toString();
	}
}