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
import org.demo.mappers.UserDao;
import org.demo.po.User;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	

    public User selectUser(int id) throws IOException {  
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = ssf.openSession();
        try {
        	
        	User user = session.selectOne("selectUser",id);
        	return user;
        }finally {
        	session.close();
        }
        
    }
    
    public List<User> selectUserByNameLike(String name,Integer id) throws IOException {  
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = ssf.openSession();
        try {
        	name="%"+name+"%";
        	 Map parmars = new HashMap<>();
        	 parmars.put("id", id);
        	 parmars.put("name", name);
        	List<User> list = session.selectList("selectUserByNameLike",parmars);
        	return list;
        }finally {
        	session.close();
        }
        
    }
    
    public User selectUserByAccount(String account) throws IOException {  
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = ssf.openSession();
        try {
        	
        	User user = session.selectOne("selectUserByAccount",account);
        	return user;

        }finally {
        	session.close();
        }
        
    }
    
    public String addUser(User user) throws IOException{
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);       
        SqlSession session = ssf.openSession();
        session.insert("insertUser",user);
        session.commit();
        session.close();
         return "true";
         }
    
}
