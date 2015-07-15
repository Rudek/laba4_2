package ua.edu.sumdu.j2ee.maxim.laba4.controllers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ua.edu.sumdu.j2ee.maxim.laba4.exceptions.IllegalSortFieldException;
import ua.edu.sumdu.j2ee.maxim.laba4.sessions.UserSessionBeanRemote;

@Controller
public class UserController {


	@Autowired
	private MessageSource messageSource;
	
	@EJB(mappedName="java:app/ejbModule-1.0/UserSessionBean!ua.edu.sumdu.j2ee.maxim.laba4.sessions.UserSessionBeanRemote")
	UserSessionBeanRemote UserBeanRemote;
	
	
    @RequestMapping(value="/login/{error}")
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    public String login(@PathVariable final String error, Locale locale, Model model ) {
    	
    	model.addAttribute("error",messageSource.getMessage(error, new Object[0], locale) );
        return "401";
    	//UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(j_username, j_password);
        //User details = new User(j_username,j_password,new Grant);
        //token.setDetails(details);
     
        /*try {
          Authentication auth = authenticationManager.authenticate(token);
          SecurityContextHolder.getContext().setAuthentication(auth);
          return new LoginStatus(auth.isAuthenticated(), auth.getName());
        } catch (BadCredentialsException e) {
          return new LoginStatus(false, null);
        }*/
    }
}
