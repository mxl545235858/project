
package org.demo.service;
//文件保存
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;
@Service
public class FileSaveService {
 
	File file = null;
	public FileSaveService(){

	 }
	
public FileSaveService(String fileName){
	file = new File(fileName);
 }	
//文件下载
	public void FileCopy(String outFileName) throws IOException {
		
		FileInputStream input = null;
		FileOutputStream output = null;
		try {
			input = new FileInputStream(file);
			output = new FileOutputStream(new File(outFileName+"/"+file.getName()));
			byte[] buf = new byte[1024];        
	           int bytesRead;        
	           while ((bytesRead = input.read(buf)) > 0) {
	               output.write(buf, 0, bytesRead);
	           }
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			input.close();  
			output.close();
		}
	}
	//获取文件大小
	public String getFileSize() {
		
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

}
