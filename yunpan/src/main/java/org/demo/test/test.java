package org.demo.test;

import java.io.IOException;
import java.util.List;

import org.demo.po.Paths;
import org.demo.service.PathService;

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
		
		PathService ps = new PathService();
		List<Paths>list=ps.selectPath(1, "/");
		for(int i=0;i<list.size();i++ ){
			System.out.println(list.get(i).getLocation());
			}
	}
}
