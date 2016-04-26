package com.cjh.eshop.util;

import java.io.File;

public class FileUtil {
	/**
	 * 删除文件
	 * @param path
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		} else {
		    System.out.println("文件不存在");
		}
	}
	
	// 获取文件格式，没有格式返回null
	public static String getExt(String fileName) {
	    int index = fileName.lastIndexOf(".");
	    if (index == -1) {
	        return null;
	    }
	    return fileName.substring(index+1,fileName.length()).toLowerCase();  
	}
}
