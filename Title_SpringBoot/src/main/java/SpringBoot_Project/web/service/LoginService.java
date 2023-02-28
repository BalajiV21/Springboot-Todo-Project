package SpringBoot_Project.web.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	
	
	public boolean isvalidate(String userid,String password) {
		
		return userid.equalsIgnoreCase("in28Minutes") && password.equalsIgnoreCase("dummy");
	}
	

}