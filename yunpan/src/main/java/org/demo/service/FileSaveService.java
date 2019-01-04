
package org.demo.service;
//文件保存
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
@Service
public class FileSaveService {
//文件下载
	public void fileCopy(HttpServletResponse response,String location) throws IOException {
		
		FileInputStream input = null;
		OutputStream output = null;
		try {
			File file = new File(location);
			input = new FileInputStream(file);
			output = response.getOutputStream();

	         byte buffer[] = new byte[1024];
	         int len = 0;

	         while((len=input.read(buffer))>0){

	             output.write(buffer, 0, len);
	         }
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			input.close();  
			output.close();
		}
	}
	
	//文件上传
		public void fileUpLoad(File file,String outFileName) throws IOException {
			
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
		
		
	
	

}
