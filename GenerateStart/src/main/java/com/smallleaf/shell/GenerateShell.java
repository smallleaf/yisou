package com.smallleaf.shell;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/***
 *
 * @author yes
 */
public class GenerateShell {
	
	public static void main(String[] args) {
		
		//生成启动脚本
		generateStartShell(args[0],args[1],args[2],args[3]);
	}
	
	/**
	 * 生成启动脚本
	 * filePath 生成的文件路径
	 * libPath 依赖包的路径
	 * main 启动的main方法
	 * projectName 项目名称
	 */
	private static void generateStartShell(String filePath,String libPath,String main,String projectName) {
		
		File libFolder= new File(libPath);
		File[] libFile = libFolder.listFiles();
		
		StringBuffer startBuffer = new StringBuffer();
		startBuffer.append("echo ' the "+projectName+" is starting...' \n");
		startBuffer.append("nohup java -cp ../classes:");
		for (File jarFile : libFile) {
			String jarName = jarFile.getName();
			startBuffer.append("../lib/").append(jarName).append(":");
		}
		String startShell =startBuffer.toString();
		startShell=startShell.substring(0, startShell.length()-1);
		startShell=startShell+" "+main;
		//不需要输入到nohup日志中
		startShell+=" >/dev/null 2>&1 & \n";
		startShell=startShell+"echo 'the "+projectName +" has started...'";
		File desFile =new File(filePath+"/start.sh");
		try {
			FileUtils.writeStringToFile(desFile, startShell);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
