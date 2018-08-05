package com.eay.cloud.core.search.test.ectemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class FileMain {

	public static void main(String[] args) {
		FileMain muDemo = new FileMain();
		String path = FileMain.getProjectName();
		FileMain.writeFile(path);
	}
	
	public static String getProjectName() {
		try {
			File directory = new File("");// 参数为空
			String courseFile = directory.getCanonicalPath();
			System.out.println("-3---" + courseFile);
			return courseFile;
		}catch (Exception e) {
			System.out.println("error");
			// TODO: handle exception
		}
		return null;
	}

	public static void writeFile(String path) {
		if(path != null) {
			try {
				String fileName = path + "\\src\\main\\java\\a.txt";
				FileWriter writer = new FileWriter(fileName);
				writer.write("Hello Kuka:\n");
				writer.write("  My name is coolszy!\n");
				writer.write("  I like you and miss you.");
				writer.close();
			} catch (Exception e) {
				System.out.println("error");
				// TODO: handle exception
			}
		}
		
	}

}