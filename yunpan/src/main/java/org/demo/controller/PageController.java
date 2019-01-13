package org.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.demo.po.Paths;
import org.demo.po.User;
import org.demo.service.PathService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	
	PathService ps = new PathService();

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping("index2")
    public String index2(){
        return "index2";
    }
    
    @RequestMapping("index3")
    public String index3(HttpSession session,Model m){
    	
    	User user=(User)session.getAttribute("user");
		session.setAttribute("path", "/");
		session.setAttribute("location", user.getfilepath());
		try {
		List<Paths> dirpaths = ps.selectDirPath(user.getId(), "/");
		List<Paths> otherspaths = ps.selectOthersPath(user.getId(), "/");
		m.addAttribute("dirpaths", dirpaths);
		m.addAttribute("otherspaths", otherspaths);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "index3";
    }
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
    
    @RequestMapping("loginout")
    public String loginout(HttpSession session){
    	session.removeAttribute("user");
        return "index";
    }
    
   
    
    
    
    
}
