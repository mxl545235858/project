package org.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demo.po.Friend;
import org.demo.po.FriendRequest;
import org.demo.po.User;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {
	 public List<FriendRequest> selectFriendRequest(int rqid) throws IOException {  
	    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
	        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
	        SqlSession session = ssf.openSession();
	        try {
	        	
	        	List<FriendRequest> list  = session.selectList("selectFriendRequest",rqid);
	        	return list;

	        }finally {
	        	session.close();
	        }
	        
	    }
	 
    public String addFriendRequest(FriendRequest fq) throws IOException{
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);       
        SqlSession session = ssf.openSession();
        session.insert("insertFriendRequest",fq);
        session.commit();
        session.close();
         return "true";
         }
    public String deleteFriendRequest(Integer id) throws IOException{
     	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
         SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
         SqlSession session = ssf.openSession();
         session.insert("deleteFriendRequest",id);
         session.commit();
         session.close();
          return "true";
          }
    
    
}
