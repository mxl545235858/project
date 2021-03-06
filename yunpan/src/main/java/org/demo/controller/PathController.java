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
import org.demo.service.NewDirectoryService;
import org.demo.service.PathService;
import org.demo.service.ShareService;
import org.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PathController {
	UserService us = new UserService();
	PathService ps = new PathService();
	FileSaveService fss = new FileSaveService();
	NewDirectoryService nds = new NewDirectoryService();
	ShareService ss = new ShareService();
	
	  //打开文件夹
    @RequestMapping("getpath")
    public String getPath(String path,int id,HttpSession session,Model m){
    	try {
        	User user=(User)session.getAttribute("user");
    		Paths p=ps.selectPathById(id);
			List<Paths> dirpaths = ps.selectDirPath(user.getId(), path+id+"/");
			List<Paths> otherspaths = ps.selectOthersPath(user.getId(), path+id+"/");
			m.addAttribute("dirpaths", dirpaths);
			m.addAttribute("otherspaths", otherspaths); 		
    		String loc = session.getAttribute("location").toString();
    		loc=loc+p.getName()+"/";
    		session.removeAttribute("location");
    		session.setAttribute("location",loc);
    		session.removeAttribute("path");
    		session.setAttribute("path",path+id+"/");
    		return "myfile";
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}finally{
    			return "myfile";

    		}
    }
    
    
    
    //上一级目录
    @RequestMapping("pageup")
    public String pageUp(HttpSession session,Model m){
    	try {
    		String path = session.getAttribute("path").toString();
    		String loc = session.getAttribute("location").toString();
    		if(!path.equals("/")){
    		String[] b = path.split("/");
    		String[] locs = loc.split("/");
    		String c="/";
    		String rloc="";
    			for(int i=1;i<(b.length-1);i++) {
    				c=c+b[i]+"/";	
    			}
    			for(int i=0;i<(locs.length-1);i++) {
    				rloc=rloc+locs[i]+"/";
    			}
    		session.removeAttribute("path");
    		session.setAttribute("path",c);   			
    		session.removeAttribute("location");
    		session.setAttribute("location",rloc);   			
        	User user=(User)session.getAttribute("user");	
			List<Paths> dirpaths = ps.selectDirPath(user.getId(), c);
			List<Paths> otherspaths = ps.selectOthersPath(user.getId(), c);
			m.addAttribute("dirpaths", dirpaths);
			m.addAttribute("otherspaths", otherspaths); 
    		}
    		else{
    			User user=(User)session.getAttribute("user");
    			List<Paths> dirpaths = ps.selectDirPath(user.getId(), "/");
    			List<Paths> otherspaths = ps.selectOthersPath(user.getId(), "/");
    			m.addAttribute("dirpaths", dirpaths);
    			m.addAttribute("otherspaths", otherspaths); 
    		}

    		
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}finally{
    			return "myfile";

    		}
    }
    
  //新建文件夹
    @RequestMapping("newdir")
    public String newDir(String dirname,HttpSession session,Model m){
    	if(dirname.equals("")){
    		return "fail";
    	}
    	
    	try {
    		  User user=(User)session.getAttribute("user");
    		  Paths paths=ps.selectDirPathsByName(user.getId(),dirname,session.getAttribute("path").toString());
    		  Paths newPath=new Paths();
    		  	if((paths==null)||!(paths.getType().equals("dir"))){
    		  		nds.NewDirectory(session.getAttribute("location").toString(),dirname);
    		  		newPath.setUid(user.getId());
    		  		newPath.setName(dirname);
    		  		newPath.setType("dir");
    		  		newPath.setPath(session.getAttribute("path").toString());
    		  		newPath.setSize("");
    		  		newPath.setLocation(session.getAttribute("location").toString()+dirname+"/");	
    		  	}else{
    		  		int i=0;
    		  		String s =dirname;
    				while(ps.selectDirPathsByName(user.getId(),s, session.getAttribute("path").toString())!=null) {		
    					i++;
    					s = dirname+"("+i+")";
    				}
    				nds.NewDirectory(session.getAttribute("location").toString(),dirname);
    				newPath.setUid(user.getId());
    		  		newPath.setName(s);
    		  		newPath.setType("dir");
    		  		newPath.setPath(session.getAttribute("path").toString());
    		  		newPath.setSize("");
    		  		newPath.setLocation(session.getAttribute("location").toString()+s+"/");
    		  	}
    		  ps.insertPath(newPath);
				List<Paths> dirpaths = ps.selectDirPath(user.getId(), session.getAttribute("path").toString());
				List<Paths> otherspaths = ps.selectOthersPath(user.getId(),session.getAttribute("path").toString());
				m.addAttribute("dirpaths", dirpaths);
				m.addAttribute("otherspaths", otherspaths); 
			  
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "myfile";
    }
    
    public void fileDelete(int uid,Paths path){
    	List<Paths> list;
		try {
			if(path.getType().equals("dir")){
				list = ps.selectPath(uid, path.getPath()+path.getId()+"/");
					if(list.size()>0){
							for(int i=0;i<list.size();i++){
								if(list.get(i).getType().equals("dir")){
									fileDelete(uid,list.get(i));
								}else{
									File file= new File(list.get(i).getLocation());
									file.delete();
								}
							}
							File file = new File(path.getLocation());
							file.delete();	
							List<Integer> idList = new ArrayList<Integer>();
	    						for(int i=0;i<list.size();i++){
	    							Integer v=list.get(i).getId();
	    							idList.add(v);
	    						}
	    					ps.deletePathList(idList);
	    					ss.deleteShareList(idList);
					}else{
						File file = new File(path.getLocation());
						file.delete();	
					}
					ps.deletePath(path.getId());
			}else{
				File file = new File(path.getLocation());
        		file.delete();
        		ps.deletePath(path.getId());
			}
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    }
    
    
    //删除文件
    @RequestMapping("deletePath")
    public String file(Integer id,HttpSession session,Model m){
    	
    	try {
    		User user=(User)session.getAttribute("user");
    		Paths path = ps.selectPathById(id);
    		fileDelete(user.getId(),path);
			List<Paths> dirpaths = ps.selectDirPath(user.getId(), session.getAttribute("path").toString());
			List<Paths> otherspaths = ps.selectOthersPath(user.getId(),session.getAttribute("path").toString());
			m.addAttribute("dirpaths", dirpaths);
			m.addAttribute("otherspaths", otherspaths); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "myfile";
    }
    
    //我的文件
    @RequestMapping("myfile")
    public String myFile(HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			session.setAttribute("path", "/");
			session.setAttribute("location", user.getfilepath());
			List<Paths> dirpaths;
			dirpaths = ps.selectDirPath(user.getId(), "/");
			List<Paths> otherspaths = ps.selectOthersPath(user.getId(), "/");
			m.addAttribute("dirpaths", dirpaths);
			m.addAttribute("otherspaths", otherspaths);
			return "myfile";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
  //文件查找
    @RequestMapping("findfile")
    public String findFile(String filename,HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			List<Paths> dirpaths = ps.selectDirPathsLikeName(user.getId(),filename);
			List<Paths> otherspaths = ps.selectOthersPathsLikeName(user.getId(),filename);
			m.addAttribute("dirpaths", dirpaths);
			m.addAttribute("otherspaths", otherspaths);
			return "index5";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
    
  
    
    //上传文件
    @RequestMapping("file")
    public String file(HttpSession session,File file,Model m){
    		User user=(User)session.getAttribute("user");
    	try {
    		Paths p =ps.selectPathsByName(user.getId(), file.getName(),session.getAttribute("path").toString());
			String fileName=file.getName();	
			String fileTyle=fileName.substring(fileName.lastIndexOf("."),fileName.length());
			if(p!=null){
    		if((fileName.equals(p.getName()))&&(fileTyle.equals(p.getType()))){
    			return "1";
    		}
			}
    		fss.fileUpLoad(file,session.getAttribute("location").toString());
			Paths paths=new Paths();
			  paths.setUid(user.getId());
	          paths.setName(file.getName());
	          paths.setType(fileTyle);
	          paths.setPath(session.getAttribute("path").toString());
	          paths.setSize(new GetFileSize().getFileSize2(file.length()));
	          paths.setLocation(session.getAttribute("location").toString()+file.getName());    
			  ps.insertPath(paths);		  
				List<Paths> dirpaths = ps.selectDirPath(user.getId(), session.getAttribute("path").toString());
				List<Paths> otherspaths = ps.selectOthersPath(user.getId(),session.getAttribute("path").toString());
				m.addAttribute("dirpaths", dirpaths);
				m.addAttribute("otherspaths", otherspaths); 	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
        return "myfile";
    }
    
    
    
    
    //下载
    @RequestMapping("donlowd")
    public String donlowd(@RequestParam("location")String location,HttpServletResponse response,HttpSession session,Model m){
    	
    	try {
    		User user = (User)session.getAttribute("user");
    		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(location, "UTF-8"));	
    		fss.fileCopy(response,location);
			List<Paths> dirpaths = ps.selectDirPath(user.getId(), session.getAttribute("path").toString());
			List<Paths> otherspaths = ps.selectOthersPath(user.getId(),session.getAttribute("path").toString());
			m.addAttribute("dirpaths", dirpaths);
			m.addAttribute("otherspaths", otherspaths); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "myfile";
    }
  //重命名
    @RequestMapping("rename")
    public String rename(Integer id,String rename,HttpSession session,Model m){
    	try {
    		User user = (User)session.getAttribute("user");
    		Paths p =ps.selectPathById(id);
    		
    		Paths paths =ps.selectPathsByNameAndType(user.getId(),rename,session.getAttribute("path").toString(),p.getType());
			if(paths!=null){
    		if(p.getType().equals(p.getType())){
    			return "fail";
    		}
			}
    				File file= new File(p.getLocation());
    				String[] sp = p.getLocation().split("/");
    				String s ="";
    					for(int i=0;i<sp.length-1;i++){
    						s=s+sp[i]+"/";
    					}	
    				s=s+rename+p.getLocation().substring(p.getLocation().lastIndexOf("."),p.getLocation().length());
    				file.renameTo(new File(s));
    				ps.updatePathLocation(rename, id,s);

	
			List<Paths> dirpaths = ps.selectDirPath(user.getId(), session.getAttribute("path").toString());
			List<Paths> otherspaths = ps.selectOthersPath(user.getId(),session.getAttribute("path").toString());
			m.addAttribute("dirpaths", dirpaths);
			m.addAttribute("otherspaths", otherspaths); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "myfile";
    }
    
}
