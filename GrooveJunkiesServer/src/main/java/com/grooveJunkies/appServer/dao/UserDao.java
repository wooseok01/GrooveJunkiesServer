package com.grooveJunkies.appServer.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.grooveJunkies.appServer.mapper.inferface.RegisterInterface;
import com.grooveJunkies.appServer.mapper.inferface.UserDaoInterface;
import com.grooveJunkies.appServer.model.UserInformation;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate template;
	@Autowired
	private SqlSession sqlSession;
	
	//public UserDao(){}
	
	public void setTemplate(JdbcTemplate template) {this.template = template;}
	public JdbcTemplate getTemplate() {return template;}
	
	public void setSqlSession(SqlSession sqlSession) {this.sqlSession = sqlSession;}
	public SqlSession getSqlSession() {return sqlSession;}
	
	public UserInformation test(){
		return sqlSession.getMapper(UserDaoInterface.class).test();
	}
	public void registerUser(UserInformation userInfo) {
		sqlSession.getMapper(RegisterInterface.class).registerUser(userInfo);
	}
	public boolean chechId(String email) {
		int exist = sqlSession.getMapper(RegisterInterface.class).checkId(email);
		if(exist != 0)
			return false;
		
		return true;
	}
	public boolean loginAction(String email, String password) {
		int result = sqlSession.getMapper(UserDaoInterface.class).loginAction(email, password);
		if(result == 1){
			return true;
		}
		return false;
	}
	
	
}
