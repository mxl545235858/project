package org.demo.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.demo.help.GetFileSize;
import org.demo.po.Friend;
import org.demo.po.FriendRequest;
import org.demo.po.Paths;
import org.demo.po.Share;
import org.demo.po.User;
import org.demo.service.FileSaveService;
import org.demo.service.FriendRequestService;
import org.demo.service.FriendService;
import org.demo.service.NewDirectoryService;
import org.demo.service.PathService;
import org.demo.service.ShareService;
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
	FriendService fs = new FriendService();
	FriendRequestService fqs = new FriendRequestService();
	NewDirectoryService nds = new NewDirectoryService();
	ShareService ss = new ShareService();
	
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
    
    @RequestMapping("tofindfriend")
    public String tofindfriend(){
        return "findfriend";
    }
    @RequestMapping("tofriendrequest")
    public String tofriendrequest(){
        return "friendrequest";
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
			return "index3";
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
			System.out.println(filename);
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
    
    
    //登录
    @RequestMapping("login")
    public String login(String account,String password,HttpSession session,Model m){
    	try {
			User user=us.selectUserByAccount(account);
			if((account.equals(user.getAccount()))&&(password.equals(user.getPassword()))){
				session.setAttribute("user", user);
				session.setAttribute("path", "/");
				session.setAttribute("location", user.getfilepath());
				List<Paths> dirpaths = ps.selectDirPath(user.getId(), "/");
				List<Paths> otherspaths = ps.selectOthersPath(user.getId(), "/");
				m.addAttribute("dirpaths", dirpaths);
				m.addAttribute("otherspaths", otherspaths);
				return "index3";
			}else{
				return "fail";
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "fail";
        
    }
    
    //注册
    @RequestMapping("register")
    public String register(String account,String password,String name){
    	try {
    		User newuser=us.selectUserByAccount(account);
    		if(newuser!=null){
    			return "fail";
    		}
			User user=new User();
			user.setAccount(account);
			user.setPassword(password);
			user.setfilepath("D:/yunpan/file/"+account+"/");
			user.setName(name);
			us.addUser(user);
			nds.NewDirectory("D:/yunpan/file/", account);
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
    			return "index3";

    		}
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
    			return "fail";
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
        return "index3";
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
    	
        return "index3";
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
    public String file(@RequestParam("id")Integer id,HttpSession session,Model m){
    	
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
    	
        return "index3";
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
    	
        return "index3";
    }
  //重命名
    @RequestMapping("rename")
    public String rename(String rename,HttpSession session,Model m){
    	try {
    		User user = (User)session.getAttribute("user");
    		Integer id= (Integer)session.getAttribute("renameid");
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
    	
        return "index3";
    }
    
    //我的好友
    @RequestMapping("myfriend")
    public String myFriend(HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			List<Friend> friend = fs.selectFriend(user.getId());
			m.addAttribute("friend", friend);
			return "myfriend";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
    //删除好友
    @RequestMapping("removefriend")
    public String removeFriend(@RequestParam("fid") Integer fid,HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			fs.removeFriend(user.getId(),fid);
			fs.removeFriend(fid,user.getId());
			List<Friend> friend = fs.selectFriend(user.getId());
			m.addAttribute("friend", friend);
			return "myfriend";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
    //查找好友
    
    @RequestMapping("findfriend")
    public String findFriend(String name,HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			System.out.println(name);
			List<User> userlist = us.selectUserByNameLike(name,user.getId());
			m.addAttribute("user", userlist);
			return "findfriend";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
    //添加好友
    @ResponseBody
    @RequestMapping("addfriend")
    public String addFriend(@RequestParam("id") Integer rqid,HttpSession session){
    	
		try {
			User user=(User)session.getAttribute("user");
			FriendRequest fq = new FriendRequest();
			fq.setUid(user.getId());
			fq.setRqid(rqid);
			fq.setName(user.getName());
			fqs.addFriendRequest(fq);
			return "申请成功";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
  //查找申请
    @RequestMapping("friendrequest")
    public String friendRequest(HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			List<FriendRequest> friendrequest =fqs.selectFriendRequest(user.getId());
			m.addAttribute("friendrequest", friendrequest);
			return "friendrequest";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
  //同意申请
    @ResponseBody
    @RequestMapping("agreefq")
    public String agreeFriend(@RequestParam("uid") Integer uid,@RequestParam("id") Integer id,HttpSession session){
    	
		try {
			User user=(User)session.getAttribute("user");
			User user2 = us.selectUser(uid);
			Friend f = new Friend();
			f.setUid(user.getId());
			f.setFid(uid);
			f.setFname(user2.getName());
			Friend f2 = new Friend();
			f2.setUid(user2.getId());
			f2.setFid(user.getId());
			f2.setFname(user.getName());
			fs.insertFriend(f);
			fs.insertFriend(f2);
			System.out.println(id);
			fqs.deleteFriendRequest(id);
			return "申请成功";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
  //拒绝申请
    @ResponseBody
    @RequestMapping("unagreefq")
    public String unagreeFriend(@RequestParam("id") Integer id,HttpSession session){
    	
		try {
			System.out.println(id);
			fqs.deleteFriendRequest(id);
			return "申请成功";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
    //文件分享
    @RequestMapping("share")
    public String share(@RequestParam("id") Integer id,HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			if(ss.selectShareByUidAndPathsid(user.getId(),id)!=null){
				return "fail";
			}
			Share s = new Share();
			s.setUid(user.getId());
			s.setPathsid(id);
			ss.addShare(s);
			List<Paths> dirpaths = ps.selectDirPath(user.getId(), session.getAttribute("path").toString());
			List<Paths> otherspaths = ps.selectOthersPath(user.getId(),session.getAttribute("path").toString());
			m.addAttribute("dirpaths", dirpaths);
			m.addAttribute("otherspaths", otherspaths); 
			return "index3";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
    //我的分享
    @RequestMapping("myshare")
    public String myShare(HttpSession session,Model m){
    	
		try {
			System.out.println("hgg");
			User user=(User)session.getAttribute("user");
			List<Share> sharelist = ss.selectShareByUid(user.getId());
			List<Integer> id = new ArrayList<Integer>();
			List<Paths> list = new ArrayList<Paths>();
			if(sharelist.size()>0){
				for(int i=0;i<sharelist.size();i++){
					id.add(sharelist.get(i).getPathsid());
				}
				list = ps.selectPathsInId(id);
			}
			m.addAttribute("myshare", list);
			return "myshare";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
    //取消分享
    @RequestMapping("removeshare")
    public String removeShare(@RequestParam("pathsid")Integer pathsid,HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			ss.deleteShare(user.getId(),pathsid);
			List<Share> sharelist = ss.selectShareByUid(user.getId());
			List<Integer> id = new ArrayList<Integer>();
			List<Paths> list = new ArrayList<Paths>();
			if(sharelist.size()>0){
				for(int i=0;i<sharelist.size();i++){
					id.add(sharelist.get(i).getPathsid());
				}
				list = ps.selectPathsInId(id);
			}
			m.addAttribute("myshare", list);
			return "myshare";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
    //好友分享
    @RequestMapping("friendshare")
    public String friendShare(HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			List<Friend> friendlist=fs.selectFriend(user.getId());
			List<Integer> friendid=new ArrayList<>();
			List<Integer> friendsharepathsid=new ArrayList<>();
			List<Share> friendshare=new ArrayList<>();
			List<Object[]> list=new ArrayList<>();

			for(int i=0;i<friendlist.size();i++){
				friendid.add(friendlist.get(i).getFid());
			}		
			if(friendid.size()>0){
				friendshare=ss.selectShareInUid(friendid);
				for(int i=0;i<friendshare.size();i++){
					friendsharepathsid.add(friendshare.get(i).getPathsid());
				}
				if(friendsharepathsid.size()>0){
					List<Paths> pathslist=ps.selectPathsInId(friendsharepathsid);
					for(int i=0;i<pathslist.size();i++){
						Object[] o=new Object[3];
						o[0]=pathslist.get(i).getName();
						o[1]=us.selectUser(pathslist.get(i).getUid()).getName();
						o[2]=pathslist.get(i).getLocation();
						list.add(o);
					}
				}
			}
			m.addAttribute("friendshare", list);
			return "friendshare";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    //下载好友分享
    @RequestMapping("donlowdfriendshare")
    public String donlowdFriendShare(@RequestParam("location")String location,HttpServletResponse response,HttpSession session,Model m){
    	
    	try {
    		User user = (User)session.getAttribute("user");
    		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(location, "UTF-8"));	
    		fss.fileCopy(response,location);
    		
    		
			List<Friend> friendlist=fs.selectFriend(user.getId());
			List<Integer> friendid=new ArrayList<>();
			List<Integer> friendsharepathsid=new ArrayList<>();
			List<Share> friendshare=new ArrayList<>();
			List<Object[]> list=new ArrayList<>();

			for(int i=0;i<friendlist.size();i++){
				friendid.add(friendlist.get(i).getFid());
			}
			
			friendshare=ss.selectShareInUid(friendid);

			
			for(int i=0;i<friendshare.size();i++){
				friendsharepathsid.add(friendshare.get(i).getPathsid());
			}			
			List<Paths> pathslist=ps.selectPathsInId(friendsharepathsid);

			for(int i=0;i<pathslist.size();i++){
				Object[] o=new Object[3];
				o[0]=pathslist.get(i).getName();
				o[1]=us.selectUser(pathslist.get(i).getUid()).getName();
				o[2]=pathslist.get(i).getLocation();
				list.add(o);
			}
			m.addAttribute("friendshare", list);
			return "friendshare";
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return "fail";
    }
}
