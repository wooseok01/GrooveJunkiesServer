package com.grooveJunkies.appServer.mapper.inferface;

import org.apache.ibatis.annotations.Param;

import com.grooveJunkies.appServer.model.UserInformation;

public interface UserDaoInterface {

	UserInformation test();
	int loginAction(
			@Param("email")String email, 
			@Param("pwd") String pwd);
	
	
	

}
