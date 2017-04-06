package com.grooveJunkies.appServer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grooveJunkies.appServer.dao.UserDao;

import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private String imageSavePath;
	private UserDao userDao;
	
	
	
	public HomeController(){
		this.imageSavePath = "/Users/wooseoksong/Downloads/";
	}
	
	public String getImageSavePath() {return imageSavePath;}
	public void setImageSavePath(String imageSavePath) {this.imageSavePath = imageSavePath;}

	@Autowired
	public void setUserDao(UserDao userDao) {this.userDao = userDao;}
	public UserDao getUserDao() {return userDao;}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
	@RequestMapping(value="/loginAction", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAction(
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="password", required=false) String password
			){
		HashMap<String, String> result = new HashMap<String, String>();
		
		if(userDao.loginAction(email, password)){
			result.put("result", "success");
			System.out.println("login Success!");
			return result;
		}
		result.put("result", "fail");
		return result;
	}
	
	
}
