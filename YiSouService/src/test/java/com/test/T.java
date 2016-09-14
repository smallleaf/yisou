package com.test;

import java.io.File;
import java.io.FileFilter;


public class T {
	
	public static void main(String[] args) {

			
			File file = new File("target/target/lib");
			System.out.println(file.isDirectory());
			File[] dirFiles = file.listFiles();
			
			StringBuffer startShell = new StringBuffer();
			startShell.append("Java -cp classes:");
			for (File jarFile : dirFiles) {
				String jarName = jarFile.getName();
				startShell.append("lib/").append(jarName).append(":");
			}
			startShell.subSequence(0, startShell.length()-1);
			startShell.append(" com.smallleaf.shell.GenerateStart");
			System.out.println(startShell.toString());
//			File desFile =new File("target/target/start.sh");
//			try {
////				FileUtils.writeStringToFile(desFile, startShell.toString());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}
}
