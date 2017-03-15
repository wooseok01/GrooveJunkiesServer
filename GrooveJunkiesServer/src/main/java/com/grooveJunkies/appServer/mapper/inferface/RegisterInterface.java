package com.grooveJunkies.appServer.mapper.inferface;

import com.grooveJunkies.appServer.model.UserInformation;

public interface RegisterInterface {
	int checkId(String email);
	void registerUser(UserInformation userInfo);
}
