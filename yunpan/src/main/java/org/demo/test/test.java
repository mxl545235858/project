package org.demo.test;

import java.io.File;
import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {

//		UserService us = new UserService();
//		ProjectPathGet p = new ProjectPathGet();
//		FileTraversalService fts = new FileTraversalService();
//		FileSaveService frs= new FileSaveService("F:/gittest/plugins.zip"); 
//		frs.FileCopy("F:/projecttest");
//		System.out.println(frs.getFileSize()); 


		
//		List<String> filesName=fts.FileTraversal("F:/gittest");
//		for(int i=0;i<filesName.size();i++) {
//			System.out.println(filesName.get(i));
//		}
		
//		NewDirectoryService nds = new NewDirectoryService();
//		nds.NewDirectory("F:/gittest", "123");
		
		//us.selectUser();
		//us.addUser();
//		List<String> filesName=fts.FileTraversal(p.PathGet()+"File/test");
//		for(int i=0;i<filesName.size();i++ ){
//		System.out.println(filesName.get(i));
//		}
//		
//		FileSaveService fss = new FileSaveService(p.PathGet()+"File/test/"+filesName.get(0));
//		fss.FileCopy("F:/gittest");
		File file =new File("F:/yunpan/file/test/1222/");
		file.delete();
		
	}
}
