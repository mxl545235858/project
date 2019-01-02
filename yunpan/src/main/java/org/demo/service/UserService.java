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
        	for(User u : list) {
        		System.out.println("id:"+u.getId());	
        	}

        }finally {
        	session.close();
        }
        
    }
    
    public String addUser() throws IOException{
    	InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        
        SqlSession session = ssf.openSession();
    	User user = new User();
        user.setAccount("test");
        user.setPassword("name");
        session.insert("insertUser",user);
        session.commit();
        session.close();
         return "true";
         }
    
}
