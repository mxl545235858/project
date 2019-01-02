package org.demo.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.demo.help.ProjectPathGet;
import org.demo.service.FileTraversalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
    @RequestMapping({"/","/login"})
    public String index(){
        return "index";
    }
    
    @RequestMapping("get")
    public String index2(Model m){
    	ProjectPathGet p = new ProjectPathGet();
    	FileTraversalService fts = new FileTraversalService();
    	System.out.println(p.PathGet()+"File/test");
    	List<String> filesName=fts.FileTraversal("F:/Project/project/yunpan/"+"File/test");
    	 m.addAttribute("filesName", filesName);
        return "index2";
    }
    
    @RequestMapping("file")
    public String file(HttpSession session,File file,Model m){
    	session.setAttribute(file.getName(), file);
    	m.addAttribute("filesName", file.getName());
        return "index2";
    }
    @ResponseBody
    @RequestMapping("xiazai")
    public String xizai(@RequestParam("fileName")String filePath,Model m){
    	
    	System.out.println(filePath);
    	return filePath;
    }
}
