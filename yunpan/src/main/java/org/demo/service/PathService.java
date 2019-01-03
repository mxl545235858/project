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
import org.demo.help.GetFileSize;
import org.demo.po.Paths;
import org.springframework.stereotype.Service;

@Service
public class PathService {
   public List<Paths> selectPath(int uid, String path) throws IOException{
	   
	   InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
       SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
       SqlSession session = ssf.openSession();
       try {
    	   Map parmars = new HashMap<>();
 
           parmars.put("uid", uid);
           parmars.put("path", path);

       	List<Paths> list = session.selectList("selectPaths",parmars);
       	return list;

       }finally {
       	session.close();
       }	   
   }
   
public Paths selectPathById(int id) throws IOException{
	   
	   InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
       SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
       SqlSession session = ssf.openSession();
       try {
       	Paths list = session.selectOne("selectPathsById",id);
       	return list;

       }finally {
       	session.close();
       }	   
   }
   
   public String insertPath(Paths paths) throws IOException{
      	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
          SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
          SqlSession session = ssf.openSession();
          session.insert("insertPaths",paths);
          session.commit();
          session.close();
           return "true";
           }
   
   public String deletePath(Integer id) throws IOException{
     	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
         SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
         SqlSession session = ssf.openSession();
         session.insert("deletePaths",id);
         session.commit();
         session.close();
          return "true";
          }


}
