package org.demo.service;

import java.io.File;

import org.springframework.stereotype.Service;
@Service
public class FileDeleteService {
	public void fileDelete(File file){
		
		file.delete();
		
	}
}
