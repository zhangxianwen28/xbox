package com.xw.util.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPServerUtil {

	public static String doPostRequest(String servletAddress, Object content) {
		PrintWriter out = null;
		BufferedReader in = null;
		String resoult = "";
		try {
			// System.out.println("要发送的信息是："+content);
			/* 拼接url,Android这里需要换上远程地址，因为Android端没办法访问localhost，java的话本地tomcat运行的话倒是无妨 */
			// String address =
			// "http://10.2.17.45:8080/wsx/wsxExternalServlet/doWsxData.action";
			URL url = new URL(servletAddress);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			// 这两个参数必须加
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			// 设置超时时间
			httpURLConnection.setReadTimeout(10 * 10000);
			httpURLConnection.setConnectTimeout(10 * 10000);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.connect();

			out = new PrintWriter(httpURLConnection.getOutputStream());
			// 在输出流中写入参数
			out.print(content);
			out.flush();

			if (httpURLConnection.getResponseCode() == 200) {
				in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
				String line;
				while ((line = in.readLine()) != null) {
					resoult += line;
				}
			}

			return resoult;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
