package org.demo.service;
//文件查找
import java.io.File;

import org.springframework.stereotype.Service;
@Service
public class FindFileService {
	public String FindFile(String fileName) {
		File file = new File(fileName);
		if(file.exists()) {
			return "1";
		}
		return "0";
	}

}
