package org.demo.service;

import java.io.File;

import org.omg.CORBA.portable.OutputStream;
import org.springframework.stereotype.Service;

//新建文件夹
@Service
public class NewDirectoryService {

	public void NewDirectory(String path,String name) {
		File file = new File(path+"/"+name);
		if(!file.exists()) {
			file.mkdirs();
		}
		else {
			int i=0;
			while(file.exists()) {		
				i++;
				file = new File(path+"/"+name+"("+i+")");
			}
			file.mkdirs();

		}
	}
}
