package org.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
	

    public void selectUser() throws IOException {  
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = ssf.openSession();
        try {
        	
        	List<User> list = session.selectList("selectUser");
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
