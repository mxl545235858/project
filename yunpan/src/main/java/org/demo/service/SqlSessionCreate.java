package org.demo.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;
@Service
public  class SqlSessionCreate {
 public SqlSession CreateSession()throws IOException{

	 InputStream is = Resources.getResourceAsStream("org/demo/config/config.xml");
     SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
     
     return ssf.openSession();

	 
 }
}
