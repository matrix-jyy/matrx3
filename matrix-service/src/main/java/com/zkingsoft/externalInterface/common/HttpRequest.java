package com.zkingsoft.externalInterface.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

public class HttpRequest {
	public static final String SEND_POST = "POST";
	public static final String SEND_GET = "GET";
	Logger log=Logger.getLogger(HttpRequest.class);
	private HttpRequest() {
	}

	public static HttpRequest createHttpRequest() {
		return new HttpRequest();
	}

	public HttpResponse sendHttpGet(String urlString, Map<String, String> params) throws IOException {

		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append((String) params.get(key));
				i++;
			}
			urlString = urlString + param;
		}
		log.info("sent http get url=" + urlString);
		URL url = new URL(urlString);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);

		HttpResponse response = new HttpResponse();
		response.setInputStream(urlConnection.getInputStream());
		response.setContentType(urlConnection.getContentType());
		response.setContentLength(urlConnection.getContentLength());

		response.setFileName(urlConnection.getRequestProperty("filename"));
		return response;
	}

	public HttpResponse sendHttpPost(String urlString, Map<String, String> params, String filePath, String contentType)
			throws IOException {
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append((String) params.get(key));
				i++;
			}
			urlString = urlString + param;
		}

		URL url = new URL(urlString);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod("POST");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);

		File file = new File(filePath);

		urlConnection.setRequestProperty("Connection", "Keep-Alive");
		urlConnection.setRequestProperty("Charset", "UTF-8");

		String BOUNDARY = "----------" + System.currentTimeMillis();
		urlConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		OutputStream out1 = new DataOutputStream(urlConnection.getOutputStream());

		out1.write(head);

		DataInputStream in1 = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in1.read(bufferOut)) != -1) {
			out1.write(bufferOut, 0, bytes);
		}
		in1.close();

		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");

		out1.write(foot);

		out1.flush();
		out1.close();

		FileInputStream in = new FileInputStream(file);

		OutputStream out = urlConnection.getOutputStream();
		byte[] buf = new byte[1024];
		int hasRead = 0;
		while ((hasRead = in.read(buf)) > 0) {
			out.write(buf, 0, hasRead);
		}
		in.close();
		out.flush();
		out.close();

		HttpResponse response = new HttpResponse();
		response.setInputStream(urlConnection.getInputStream());

		return response;
	}

	public HttpResponse sendHttpsGet(String urlString, Map<String, String> params) throws IOException {
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append((String) params.get(key));
				i++;
			}
			urlString = urlString + param;
		}

		URL url = new URL(urlString);
		HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

		urlConnection.setRequestMethod("GET");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);

		HttpResponse response = new HttpResponse();
		response.setInputStream(urlConnection.getInputStream());
		response.setContentType(urlConnection.getContentType());
		response.setContentLength(urlConnection.getContentLength());

		return response;
	}

	public HttpResponse sendHttpsPost(String urlString, Map<String, String> params, String sendData)
			throws IOException {
		if (params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append((String) params.get(key));
				i++;
			}
			urlString = urlString + param;
		}

		URL url = new URL(urlString);
		HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

		urlConnection.setRequestMethod("POST");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);

		if (sendData != null) {
			urlConnection.getOutputStream().write(sendData.getBytes("utf-8"));
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();
		}

		HttpResponse response = new HttpResponse();
		response.setInputStream(urlConnection.getInputStream());
		response.setContentType(urlConnection.getContentType());
		response.setContentLength(urlConnection.getContentLength());

		return response;
	}
}
