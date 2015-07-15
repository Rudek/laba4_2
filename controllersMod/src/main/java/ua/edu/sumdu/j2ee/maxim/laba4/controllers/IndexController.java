package ua.edu.sumdu.j2ee.maxim.laba4.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.edu.sumdu.j2ee.maxim.laba4.sessions.ProductSessionBeanRemote;

@Controller
public class IndexController {

	
	//@EJB(mappedName="java:global/ejbModule-1_0/ProductSessionBean!ua.edu.sumdu.j2ee.maxim.laba4.sessions.ProductSessionBeanRemote")
	@EJB(mappedName="java:app/ejbModule-1.0/ProductSessionBean!ua.edu.sumdu.j2ee.maxim.laba4.sessions.ProductSessionBeanRemote")
	//@EJB(mappedName="ProductSessionBean")
	//@EJB(mappedName="ProductSessionBean/ProductSessionBeanRemote");
	ProductSessionBeanRemote productBeanRemote;
	
    @RequestMapping(value="/")
    public String listProducts(Map<String, Object> map, HttpServletRequest request) throws MalformedURLException {
	    map.put("products", productBeanRemote.getLast6Products());	
        return "index";
    }
    
    // for 403 access denied page
 	@RequestMapping(value = "/403", method = RequestMethod.GET)
 	public String accesssDenied() {
 		return "403";
  
 	}

}
