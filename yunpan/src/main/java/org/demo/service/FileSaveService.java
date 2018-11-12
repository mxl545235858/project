
package org.demo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
@Service
public class FileSaveService {
	
	public void FileReader(String fileName) {
		File file = new File(fileName);	
		InputStream in = null;
		try {
			in = new FileInputStream(file);	
			FileReader filer = new FileReader(fileName);
			BufferedReader br = new BufferedReader(filer);
//			int tempbyte;  
//			String s;
			System.out.println(file.length());
//			while ((s = br.readLine()) != null) {  
//				System.out.print(s);  
//            }  
//			 
//            while ((tempbyte = in.read()) != -1) {  
//                System.out.write(tempbyte);  
//            }  
            in.close();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
	}

}
