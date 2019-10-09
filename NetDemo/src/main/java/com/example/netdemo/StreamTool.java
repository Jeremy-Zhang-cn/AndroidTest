package com.example.netdemo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

class StreamTool {

	/**
	 * 把一个inputstream里面的内容转化成一个byte[]
	 */

	public static byte[] read(InputStream is) throws Exception{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int len = 0;
		while((len = is.read(buffer))!=-1){
			bos.write(buffer, 0, len);
		}
		is.close();
		bos.flush();
		byte[] result = bos.toByteArray();
		System.out.println(new String(result));
		return  result;
	}
}
