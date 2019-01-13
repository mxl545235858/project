package org.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demo.po.Friend;
import org.demo.po.User;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    public List<Friend> selectFriend(int uid) throws IOException {  
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = ssf.openSession();
        try {
        	
        	List<Friend> list  = session.selectList("selectFriend",uid);
        	return list;

        }finally {
        	session.close();
        }
        
    }
    
    public List<Friend> selectFriendLive(int uid,int fid) throws IOException {  
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = ssf.openSession();
        try {
            Map parmars = new HashMap<>();    
            parmars.put("uid", uid);
            parmars.put("fid", fid);
        	List<Friend> list  = session.selectList("selectFriendLive",parmars);
        	return list;

        }finally {
        	session.close();
        }
        
    }
    
    public String insertFriend(Friend f) throws IOException{
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);       
        SqlSession session = ssf.openSession();
        session.insert("insertFriend",f);
        session.commit();
        session.close();
         return "true";
         }
    
    public String removeFriend(Integer uid,Integer fid) throws IOException{
     	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
         SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
         SqlSession session = ssf.openSession();
         Map parmars = new HashMap<>();    
         parmars.put("uid", uid);
         parmars.put("fid", fid);
         session.insert("removefriend",parmars);
         session.commit();
         session.close();
          return "true";
          }
    
    
    
}
