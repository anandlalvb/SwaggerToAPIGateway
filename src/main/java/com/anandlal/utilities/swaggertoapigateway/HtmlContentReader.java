package com.anandlal.utilities.swaggertoapigateway;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Util class to read html content of an url and return as byte array.
 */
public class HtmlContentReader {

	/**
	 * Static method to read html content of an url and return as byte array.
	 * @param url
	 * @return
	 */
	public static byte[] readFromUrl(String urlStr){
		byte[] byteArray = null;
		try {
			System.out.println("Content reading from url:" + urlStr);
			URL url = new URL(urlStr);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentType();
			encoding = encoding == null ? "UTF-8" : encoding;
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		    int nRead;
		    byte[] data = new byte[1024];
		   
			while ((nRead = in.read(data, 0, data.length)) != -1) {
			    buffer.write(data, 0, nRead);
			} 
		    buffer.flush();
		    byteArray = buffer.toByteArray();
		    System.out.println("Content read succesfully from url : " +urlStr);
		} catch (IOException e) {
			System.out.println("ERROR while reading content");
			System.err.println();
			e.printStackTrace();
		}
		return byteArray;
	}
}
