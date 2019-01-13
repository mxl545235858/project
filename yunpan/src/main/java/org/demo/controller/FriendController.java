package org.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.demo.po.Friend;
import org.demo.po.FriendRequest;
import org.demo.po.User;
import org.demo.service.FriendRequestService;
import org.demo.service.FriendService;
import org.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FriendController {
	
	UserService us = new UserService();
	FriendService fs = new FriendService();
	FriendRequestService fqs = new FriendRequestService();

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
    public String addFriend(Integer rqid,HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			System.out.println(rqid);
			List<FriendRequest> fqlist = fqs.selectFriendRequestLive(user.getId(),rqid);
			List<Friend> flist = fs.selectFriendLive(user.getId(), rqid);
			if(fqlist.size()>0){
				return "2";
			}else if(flist.size()>0){
				return "3";
			}
			FriendRequest fq = new FriendRequest();
			fq.setUid(user.getId());
			fq.setRqid(rqid);
			fq.setName(user.getName());
			fqs.addFriendRequest(fq);
			return "1";
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
    @RequestMapping("agreefq")
    public String agreeFriend(Integer uid,Integer id,HttpSession session,Model m){
    	
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
			fqs.deleteFriendRequest(id);
			List<FriendRequest> friendrequest =fqs.selectFriendRequest(user.getId());
			m.addAttribute("friendrequest", friendrequest);
			return "friendrequest";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }
    
  //拒绝申请
    @RequestMapping("unagreefq")
    public String unagreeFriend(@RequestParam("id") Integer id,HttpSession session,Model m){
    	
		try {
			User user=(User)session.getAttribute("user");
			fqs.deleteFriendRequest(id);
			List<FriendRequest> friendrequest =fqs.selectFriendRequest(user.getId());
			m.addAttribute("friendrequest", friendrequest);
			return "friendrequest";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
    }

}
