package ua.edu.sumdu.j2ee.maxim.laba4.interceptors;

import java.io.IOException;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.CategoryLevel;
import ua.edu.sumdu.j2ee.maxim.laba4.services.HierarchyCategoryService;
import ua.edu.sumdu.j2ee.maxim.laba4.sessions.CategorySessionBeanRemote;

public class AllRequestInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	HierarchyCategoryService hierarchyCategoryService;
	
	@EJB(mappedName="java:app/ejbModule-1.0/CategorySessionBean!ua.edu.sumdu.j2ee.maxim.laba4.sessions.CategorySessionBeanRemote")
	CategorySessionBeanRemote categoryBeanRemote;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
            				 HttpServletResponse response, 
            				 Object handler) {
		request.setAttribute("jsonCategories", hierarchyCategoryService.getCategoriesJSON(categoryBeanRemote.getHierarchyCategories()) );
		return true;
	}

}
