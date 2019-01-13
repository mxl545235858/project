package org.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.demo.po.Paths;
import org.demo.po.User;
import org.demo.service.NewDirectoryService;
import org.demo.service.PathService;
import org.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	UserService us = new UserService();
	PathService ps = new PathService();
	NewDirectoryService nds = new NewDirectoryService();

	
	//登录
    @ResponseBody
    @RequestMapping("login")
    public String login(String account,String password,HttpSession session,Model m){
    	try {
			User user=us.selectUserByAccount(account);
			if(user==null){
				return "2";
			}
			if((account.equals(user.getAccount()))&&(password.equals(user.getPassword()))){
				session.setAttribute("user", user);
				session.setAttribute("path", "/");
				session.setAttribute("location", user.getfilepath());
				List<Paths> dirpaths = ps.selectDirPath(user.getId(), "/");
				List<Paths> otherspaths = ps.selectOthersPath(user.getId(), "/");
				m.addAttribute("dirpaths", dirpaths);
				m.addAttribute("otherspaths", otherspaths);
				return "1";
			}else{
				return "2";
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "fail";
        
    }
    
    //注册
    @ResponseBody
    @RequestMapping("register")
    public String register(String account,String password,String name){
    	try {
    		User newuser=us.selectUserByAccount(account);
    		if(newuser!=null){
    			return "2";
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
    	return "1";
        
    }

}
