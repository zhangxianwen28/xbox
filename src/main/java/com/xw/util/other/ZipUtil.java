package com.xw.util.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	
	private final static String encoding = "UTF-8";
	/**
	 * compress data to zip format
	 * @param str
	 * @return
	 */
	public static byte[] compressString (String str){
		try {
			if (str == null) {
				return null;
			}
			
			ByteArrayOutputStream out = null; 
			ZipOutputStream zout = null; 
			byte[] compressed ;
			
			out = new ByteArrayOutputStream(); 
			zout = new ZipOutputStream(out); 
			zout.putNextEntry(new ZipEntry("NodeClob")); 
			zout.write(str.getBytes(encoding)); 
			zout.closeEntry(); 
			compressed = out.toByteArray();
			
			out.close();
			zout.close();
			
			return compressed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * decompress data from zip format
	 * @param
	 * @return
	 * @throws
	 */
	public static String decompressToString (byte[] source){
		try {
			if (source == null) {
				return null;
			}
			
			ByteArrayOutputStream out = null; 
			ByteArrayInputStream in = null; 
			ZipInputStream zin = null; 
			String decompressed = ""; 
			
			out = new ByteArrayOutputStream(); 
			in = new ByteArrayInputStream(source);
			zin = new ZipInputStream(in); 
			zin.getNextEntry(); 
			
			byte[] buffer = new byte[2048]; 
			int offset = -1; 
			while((offset = zin.read(buffer)) != -1) { 
			    out.write(buffer, 0, offset); 
			} 
			
			decompressed = out.toString(encoding); 
			
			zin.close();
			in.close();
			out.close();
			
			return decompressed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
