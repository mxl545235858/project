package org.demo.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.demo.help.GetFileSize;
import org.demo.po.Paths;
import org.demo.po.User;
import org.demo.service.FileSaveService;
import org.demo.service.PathService;
import org.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.Session;

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
    
  //首页
    @RequestMapping("index2")
    public String index2(){
        return "index2";
    }
    
    //首页
    @RequestMapping("index4")
    public String index4(@RequestParam Integer id,HttpSession session){
    	session.setAttribute("renameid", id);
        return "index4";
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
    
    //注册
    @RequestMapping("register")
    public String register(String account,String password){
    	try {
			User user=new User();
			user.setAccount(account);
			user.setPassword(password);
			us.addUser(user);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "index";
        
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
    		fss.fileUpLoad(file,"F:/Project/project/yunpan/File/"+user.getAccount());
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
    		  Paths paths=ps.selectPathsByName(dirname,session.getAttribute("path").toString());
    		  User user=(User)session.getAttribute("user");
    		  Paths newPath=new Paths();
    		  	if(paths==null){
    		  		newPath.setUid(user.getId());
    		  		newPath.setName(dirname);
    		  		newPath.setType("dir");
    		  		newPath.setPath(session.getAttribute("path").toString());
    		  		newPath.setSize("");
    		  		newPath.setLocation("");
    		  		
    		  	}else{
    		  		int i=0;
    		  		String s =dirname;
    				while(ps.selectPathsByName(s, session.getAttribute("path").toString())!=null) {		
    					i++;
    					s = dirname+"("+i+")";
    				}
    				newPath.setUid(user.getId());
    		  		newPath.setName(s);
    		  		newPath.setType("dir");
    		  		newPath.setPath(session.getAttribute("path").toString());
    		  		newPath.setSize("");
    		  		newPath.setLocation("");
    		  	}
    		  ps.insertPath(newPath);
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
    		
    		if(path.getType().equals("dir")){
    			List<Paths> list=ps.selectPathByPath(path.getPath()+path.getId()+"/");
    			if(list.size()>0){
    			for(int i=0;i<list.size();i++){
    				File file= new File(list.get(i).getLocation());
    				file.delete();
    			}
    			List<Integer> idList = new ArrayList<Integer>();
    			for(int i=0;i<list.size();i++){
    				Integer v=list.get(i).getId();
    				idList.add(v);
    			}
    			
    			ps.deletePathList(idList);
    			}
    			ps.deletePath(id);
    		}
    		else{
    			File file = new File(path.getLocation());
        		file.delete();
        		ps.deletePath(id);
    		}
    		User user=(User)session.getAttribute("user");
			List<Paths> paths = ps.selectPath(user.getId(),session.getAttribute("path").toString());
			m.addAttribute("paths", paths);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "index3";
    }
    //下载
    @RequestMapping("donlowd")
    public String donlowd(@RequestParam("location")String location,HttpServletResponse response,HttpSession session,Model m){
    	
    	try {
    		User user = (User)session.getAttribute("user");
    		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(location, "UTF-8"));	
    		fss.fileCopy(response,location);
   		    List<Paths> paths = ps.selectPath(user.getId(),session.getAttribute("path").toString());
			m.addAttribute("paths", paths);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "index3";
    }
  //重命名
    @RequestMapping("rename")
    public String rename(String rename,HttpSession session,Model m){
    	try {
    		Integer id= (Integer)session.getAttribute("renameid");
    		Paths p =ps.selectPathById(id);
    			if(!p.getType().equals("dir")){
    				File file= new File(p.getLocation());
    				String[] sp = p.getLocation().split("/");
    				String s ="";
    					for(int i=0;i<sp.length-1;i++){
    						s=s+sp[i]+"/";
    					}	
    				s=s+rename+p.getLocation().substring(p.getLocation().lastIndexOf("."),p.getLocation().length());
    				file.renameTo(new File(s));
    				ps.updatePathLocation(rename, id,s);
    			}else{
    				ps.updatePathName(rename, id);
    			}	
    		User user = (User)session.getAttribute("user");
   		    List<Paths> paths = ps.selectPath(user.getId(),session.getAttribute("path").toString());
			m.addAttribute("paths", paths);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "index3";
    }
    
    
    
}
