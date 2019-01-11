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
import org.demo.po.FriendRequest;
import org.demo.po.Share;
import org.springframework.stereotype.Service;

@Service
public class ShareService {
	
	 public List<Share> selectShareByUid(int uid) throws IOException {  
	    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
	        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
	        SqlSession session = ssf.openSession();
	        try {
	        	
	        	List<Share> list  = session.selectList("selectShareByUid",uid);
	        	return list;

	        }finally {
	        	session.close();
	        }
	        
	    }
	 

	 public Share selectShareByUidAndPathsid(Integer uid,Integer pathsid) throws IOException {  
	    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
	        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
	        SqlSession session = ssf.openSession();
	        try {
	        	Map parmars = new HashMap<>();
	       	 	parmars.put("uid", uid);
	       		parmars.put("pathsid", pathsid);
	        	Share s  = session.selectOne("selectShareByUidAndPathsid",parmars);
	        	return s;

	        }finally {
	        	session.close();
	        }
	        
	    }
	 
	 public List<Share> selectShareInUid(List<Integer> uid) throws IOException {  
	    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
	        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
	        SqlSession session = ssf.openSession();
	        try {
	        	Map parmars = new HashMap<>();
	       	 	parmars.put("uid", uid);
	        	List<Share> list  = session.selectList("selectShareInUid",parmars);
	        	return list;

	        }finally {
	        	session.close();
	        }
	        
	    }

    public String addShare(Share s) throws IOException{
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);       
        SqlSession session = ssf.openSession();
        session.insert("insertShare",s);
        session.commit();
        session.close();
         return "true";
         }
    public String deleteShare(Integer uid,Integer pathsid) throws IOException{
     	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
         SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
         SqlSession session = ssf.openSession();
         Map parmars = new HashMap<>();
    	 parmars.put("uid", uid);
    	 parmars.put("pathsid", pathsid);
         session.insert("deleteShare",parmars);
         session.commit();
         session.close();
          return "true";
          }
    
	
}
