package SpringBoot_Project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.SessionAttributes;

import SpringBoot_Project.web.service.LoginService;


@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	LoginService service;
	
	
	//Model
	
	@RequestMapping(value = "/" ,method = RequestMethod.GET)
	
    
    public String ShowLogin(ModelMap model) {
		model.put("name", "in28Minutes");
        return "welcome";
    }
	
	
		
	
}
