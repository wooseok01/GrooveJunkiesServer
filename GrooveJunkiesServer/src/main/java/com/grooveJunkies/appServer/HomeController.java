package com.grooveJunkies.appServer;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private String imageSavePath;
	
	
	
	public HomeController(){
		this.imageSavePath = "/Users/wooseoksong/Downloads/";
	}
	public String getImageSavePath() {return imageSavePath;}
	public void setImageSavePath(String imageSavePath) {this.imageSavePath = imageSavePath;}


	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		System.out.println("home!");
		return "home";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> register(Model model, 
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="password", required=false) String password,
			@RequestParam(value="stageName", required=false) String stageName){
		System.out.println("im in!");
		System.out.println(email);
		System.out.println(password);
		System.out.println(stageName);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "success");
		
		
		return result;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> androidTestWebCommunicate(HttpServletRequest request,
			@RequestParam(value="email") String email,
			@RequestParam(value="password") String password,
			@RequestParam(value="name") String name,
			@RequestParam(value="fileType") String fileType,
			@RequestPart(value="picture", required=false) MultipartFile file){
		System.out.println("im in test!");
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println("email : "+email);
		System.out.println("password : "+password);
		System.out.println("name : "+name);
		System.out.println("fileType : "+fileType);
		
		if(file == null){
			System.out.println("file is null!");
			map.put("result", "casting fail!");
			return map;
		}else{
			String emailName = email.substring(0, email.indexOf('@')-1);
			String path = imageSavePath+emailName+fileType;
			System.out.println("path : "+path);
			File saveFile = new File(path);
			System.out.println("email : "+email);
			try{
				file.transferTo(saveFile);
			}catch(Exception e){
				e.printStackTrace();
				map.put("result", "save Fail");
				return map;
			}
			
		}
		
		return map;
	}
	
	
}
