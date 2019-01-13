package org.demo.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.demo.po.Friend;
import org.demo.po.Paths;
import org.demo.po.Share;
import org.demo.po.User;
import org.demo.service.FileSaveService;
import org.demo.service.FriendService;
import org.demo.service.PathService;
import org.demo.service.ShareService;
import org.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShareController {
	
	UserService us = new UserService();
	PathService ps = new PathService();
	FileSaveService fss = new FileSaveService();
	FriendService fs = new FriendService();
	ShareService ss = new ShareService();
	
	//文件分享
	@ResponseBody
    @RequestMapping("share")
    public String share(@RequestParam("id") Integer id,HttpSession session){
    	
		try {
			User user=(User)session.getAttribute("user");
		    Share share=ss.selectShareByUidAndPathsid(user.getId(),id);
			if(share!=null){
				return "2";
			}
			Share s = new Share();
			s.setUid(user.getId());
			s.setPathsid(id);
			ss.addShare(s);
			return "1";
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
			session.setAttribute("myshare", list);
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
