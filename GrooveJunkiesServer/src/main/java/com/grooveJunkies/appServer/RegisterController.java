package com.grooveJunkies.appServer;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.grooveJunkies.appServer.dao.UserDao;
import com.grooveJunkies.appServer.model.UserInformation;

@Controller
public class RegisterController {
	private String imageSavePath;
	private UserDao userDao;
	public RegisterController() {
		this.imageSavePath = "/Users/wooseoksong/Downloads/";
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {this.userDao = userDao;}
	public UserDao getUserDao() {return userDao;}
	
	@RequestMapping(value="/checkId", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkId(Model model,
			@RequestParam(value="email", required=false) String email){
		System.out.println("im in");
		HashMap<String, String> result = new HashMap<String, String>();
		if(email == null){
			result.put("result", "false");
			return result;
		}
		boolean plag = userDao.chechId(email);
		if(plag){
			result.put("result", "ok");
			return result;
		}
		result.put("result", "false");
		return result;
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> register(Model model, 
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="password", required=true) String password,
			@RequestParam(value="name", required=true) String name,
			@RequestPart(value="picture", required=false) MultipartFile file,
			@RequestParam(value="fileType", required=false) String fileType){
		System.out.println("register function!");
		
		Map<String, String> resultOk = new HashMap<String, String>();
		resultOk.put("result", "success");
		Map<String, String> resultNo = new HashMap<String, String>();
		String fileUrl = "";
		
		if(file != null){
			String fileName = email.substring(0, email.indexOf('@'));
			fileUrl = imageSavePath + fileName + fileType;
			File saveFile = new File(fileUrl);
			
			try{
				file.transferTo(saveFile);
			}catch(Exception e){
				e.printStackTrace();
				resultNo.put("result", "save file error");
				return resultNo;
			}
		}
		try{
			name = URLDecoder.decode(name,"UTF-8");
		}catch(Exception e){e.printStackTrace();}
		finally {
			UserInformation userInfo = new UserInformation(email, password, name, fileUrl);
			System.out.println(userInfo.toString());
			
			userDao.registerUser(userInfo);
		}
		
		return resultOk;
	}
	
	
	

}
