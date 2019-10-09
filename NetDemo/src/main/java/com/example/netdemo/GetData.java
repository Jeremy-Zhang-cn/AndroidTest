package com.example.netdemo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData {

	//定义一个获取网络图片数据的方法
	public static byte[] getImage(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//设置连接超时为5秒
		conn.setConnectTimeout(5000);
		//设置请求类型为Get类型
		conn.setRequestMethod("GET");
		//判断请求URL是否成功
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("请求URL失败");
		}
		InputStream inputStream = conn.getInputStream();
		byte[] bt = StreamTool.read(inputStream);
		inputStream.close();
		return bt;
	}
}
