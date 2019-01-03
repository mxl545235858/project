package org.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.demo.help.GetFileSize;
import org.demo.help.ProjectPathGet;
import org.demo.po.Paths;
import org.demo.po.User;
import org.demo.service.FileSaveService;
import org.demo.service.FileTraversalService;
import org.demo.service.PathService;
import org.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	

	UserService us = new UserService();
	PathService ps = new PathService();
	FileSaveService fss = new FileSaveService();
	
	//首页
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    
    //登录
    @RequestMapping("login")
    public String login(String account,String password,HttpSession session,Model m){
    	try {
			User user=us.selectUserByAccount(account);
			if(user!=null){
				session.setAttribute("user", user);
				session.setAttribute("path", "/");
				List<Paths> paths = ps.selectPath(user.getId(), "/");
				m.addAttribute("paths", paths);
				return "index3";
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "fail";
        
    }
    
    //打开文件夹
    @RequestMapping("getpath")
    public String getPath(@RequestParam("path")String path,@RequestParam("id")int id,HttpSession session,Model m){
    	try {
        	User user=(User)session.getAttribute("user");
    		List<Paths> paths;
    		paths = ps.selectPath(user.getId(),path+id+"/");
    		m.addAttribute("paths", paths);
    		session.removeAttribute("path");
    		session.setAttribute("path",path+id+"/");
    		return "index3";
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}finally{
    			return "index3";

    		}
    }
    
    
    
    //上一级目录
    @RequestMapping("pageup")
    public String pageUp(HttpSession session,Model m){
    	try {
    		String path = session.getAttribute("path").toString();
    		if(!path.equals("/")){
    		System.out.println(path);
    		String[] b = path.split("/");
    		String c="/";
    			for(int i=1;i<(b.length-1);i++) {
    				c=c+b[i]+"/";
    			}
    		session.removeAttribute("path");
    		session.setAttribute("path",c);   			
        	User user=(User)session.getAttribute("user");
    		 List<Paths> paths = ps.selectPath(user.getId(),c);
    		m.addAttribute("paths", paths);
    		}
    		else{
    			User user=(User)session.getAttribute("user");
    			List<Paths> paths = ps.selectPath(user.getId(),"/");
    			m.addAttribute("paths", paths);
    		}

    		
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}finally{
    			return "index3";

    		}
    }
    
    
    //上传文件
    @RequestMapping("file")
    public String file(HttpSession session,File file,Model m){
    		User user=(User)session.getAttribute("user");
    	try {
    		fss.fileUpLoad(file,user.getdonlowdpath());
			Paths paths=new Paths();
			String fileName=file.getName();
			String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length());
			
			  paths.setUid(user.getId());
	          paths.setName(file.getName());
	          paths.setType(fileTyle);
	          paths.setPath(session.getAttribute("path").toString());
	          paths.setSize(new GetFileSize().getFileSize2(file.length()));
	          paths.setLocation("F:/Project/project/yunpan/File/"+user.getAccount()+"/"+file.getName());
			  ps.insertPath(paths);
			  List<Paths> path = ps.selectPath(user.getId(),session.getAttribute("path").toString());
			  m.addAttribute("paths", path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        return "index3";
    }
    
    
    //新建文件夹
    @RequestMapping("newdir")
    public String newDir(String dirname,HttpSession session,Model m){
    	
    	try {
    		  Paths paths=new Paths();
    		  User user=(User)session.getAttribute("user");
    		  paths.setUid(user.getId());
	          paths.setName(dirname);
	          paths.setType("");
	          paths.setPath(session.getAttribute("path").toString());
	          paths.setSize("");
	          paths.setLocation("");
			  ps.insertPath(paths);
			  List<Paths> path = ps.selectPath(user.getId(),session.getAttribute("path").toString());
			  m.addAttribute("paths", path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "index3";
    }
    
    
    
    //删除文件
    @RequestMapping("deletePath")
    public String file(@RequestParam("id")Integer id,HttpSession session,Model m){
    	
    	try {
    		Paths path = ps.selectPathById(id);
    		File file = new File(path.getLocation());
    		file.delete();
    		ps.deletePath(id);
    		User user=(User)session.getAttribute("user");
			List<Paths> paths = ps.selectPath(user.getId(),session.getAttribute("path").toString());
			m.addAttribute("paths", paths);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "index3";
    }
    
    
    
    
    @ResponseBody
    @RequestMapping("xiazai")
    public String xizai(@RequestParam("fileName")String filePath,Model m){
    	
    	System.out.println(filePath);
    	return filePath;
    }
}
