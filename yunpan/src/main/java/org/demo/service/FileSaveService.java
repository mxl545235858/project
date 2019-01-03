
package org.demo.service;
//文件保存
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;
@Service
public class FileSaveService {
//文件下载
	public void fileCopy(File file,String outFileName) throws IOException {
		
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
