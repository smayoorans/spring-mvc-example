package com.gurumayoo.jaffna.controller;


import com.gurumayoo.jaffna.model.Person;
import com.gurumayoo.jaffna.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller    
public class PersonController {  
   
	@Autowired
	private PersonService personService;
	
    @RequestMapping(value = "/person", method = RequestMethod.GET)  
	public String getPersonList(ModelMap model) {  
        model.addAttribute("personList", personService.listPerson());  
        return "output";  
    }  
    
    @RequestMapping(value = "/person/save", method = RequestMethod.POST)  
	public View createPerson(@ModelAttribute Person person) {
    	if(StringUtils.hasText(person.getId())) {
    		personService.updatePerson(person);
    	} else {
    		personService.addPerson(person);
    	}
    	return new RedirectView("/spring-mvc-example/person");
    }
        
    @RequestMapping(value = "/person/delete", method = RequestMethod.GET)  
	public View deletePerson(@ModelAttribute Person person) {
        personService.deletePerson(person);  
        return new RedirectView("/spring-mvc-example/person");
    }    
}
