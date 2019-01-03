package org.demo.help;

import java.io.File;
import java.text.DecimalFormat;

public class GetFileSize {
	//获取文件大小
		public String getFileSize(File file) {
			
			Long fileSize = file.length();
			String fileSizeS="";
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数
			if((0<fileSize)&&(fileSize<1024)) {
				fileSizeS=fileSize.toString()+"byte";
			}
			else if((1024<=fileSize)&&(fileSize<(1024*1024))) {
				
				fileSizeS=df.format(fileSize/(float)1024).toString()+"KB";
			}
			else if((1024*1024<=fileSize)&&(fileSize<(1024*1024*1024))) {

				fileSizeS=df.format(fileSize/(float)(1024*1024)).toString()+"MB";
			}
			else if(1024*1024*1024<=fileSize) {

				fileSizeS=df.format(fileSize/(float)(1024*1024*1024)).toString()+"GB";
			}
			
			
			
			return fileSizeS;
		}
		
		public String getFileSize2(long size) {
			
			Long fileSize = size;
			String fileSizeS="";
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数
			if((0<fileSize)&&(fileSize<1024)) {
				fileSizeS=fileSize.toString()+"byte";
			}
			else if((1024<=fileSize)&&(fileSize<(1024*1024))) {
				
				fileSizeS=df.format(fileSize/(float)1024).toString()+"KB";
			}
			else if((1024*1024<=fileSize)&&(fileSize<(1024*1024*1024))) {

				fileSizeS=df.format(fileSize/(float)(1024*1024)).toString()+"MB";
			}
			else if(1024*1024*1024<=fileSize) {

				fileSizeS=df.format(fileSize/(float)(1024*1024*1024)).toString()+"GB";
			}
			
			
			
			return fileSizeS;
		}
}
