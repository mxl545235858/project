package org.demo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

//文件夹遍历
@Service
public class FileTraversalService {
	
	public List<String> FileTraversal(String FileName) {
		File dir = new File(FileName);
		File[] files = dir.listFiles();
		List<String> filesName= new ArrayList<String>();
		System.out.println(files.length);
		for(int i=0;i<files.length;i++) {
			filesName.add(i, files[i].getName());
		}
		return filesName;
	}

}
