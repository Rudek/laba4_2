package ua.edu.sumdu.j2ee.maxim.laba4.exceptions;



import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.ejb.EJBException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerClass {

	private Logger logger = LogManager.getLogger(ExceptionHandlerClass.class.getName());

	@Autowired
	private MessageSource messageSource;
	

 	@ExceptionHandler(value=SQLException.class)
 	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView SQLExceptionHandler(Exception ex, Locale locale){
 		logger.error(messageSource.getMessage("Error.dberror", new Object[0], locale) + " " + ex.getMessage() + ".", ex);
 		ModelAndView mav = new ModelAndView("500");
 		mav.addObject("message", messageSource.getMessage("Error.dberror", new Object[0], locale) + " " + ex.getMessage() + ".");
 		return mav;
    }
 	
 	@ExceptionHandler(value=EJBException.class)
 	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView EJBExceptionHandler(Exception ex, Locale locale){
 		logger.error(messageSource.getMessage("Error.dberror", new Object[0], locale) + " " + ex.getMessage() + ".", ex);
        ModelAndView mav = new ModelAndView("500");
 		mav.addObject("message", messageSource.getMessage("Error.dberror", new Object[0], locale) + " " + ex.getMessage() + ".");
 		return mav;
    }
 	
 	/*@ExceptionHandler(value=AuthenticationException.class)
 	//@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView  AuthenticationExceptionHandler(Exception ex, Locale locale){
        ModelAndView mav = new ModelAndView("/");
 		mav.addObject("message", messageSource.getMessage("invalidUser", new Object[0], locale) );
 		return mav;
    }*/
 	
 	@ExceptionHandler(value=IOException.class)
 	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView IOExceptionHandler(Exception ex){
 		logger.error(ex);
        ModelAndView mav = new ModelAndView("500");
 		mav.addObject("message",  ex.getMessage() + ".");
 		return mav;
    }
 	
 	@ExceptionHandler(value=ProductNotFoundException.class)
 	@ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView ProductNotFoundExceptionHandler(ProductNotFoundException ex, Locale locale){
 		logger.error(messageSource.getMessage("Error.productNotFound", new Object[]{ex.getId()}, locale) + " " + ex.getMessage() + ".", ex);
        ModelAndView mav = new ModelAndView("404");
 		mav.addObject("message",messageSource.getMessage("Error.productNotFound", new Object[] {ex.getId()}, locale) + ".");
 		return mav;
    }
 	
 	@ExceptionHandler(value=CategoryNotFoundException.class)
 	@ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView CategoryNotFoundExceptionHandler(ProductNotFoundException ex, Locale locale){
 		logger.error(messageSource.getMessage("Error.categoryNotFound", new Object[0], locale) + " " + ex.getMessage() + ".", ex);
        ModelAndView mav = new ModelAndView("404");
 		mav.addObject("message",messageSource.getMessage("Error.categoryNotFound", new Object[] {ex.getId()}, locale) + ".");
 		return mav;
    }
 	
 	@ExceptionHandler(value=IllegalSortFieldException.class)
 	@ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView IllegalSortFieldExceptionHandler(IllegalSortFieldException ex, Locale locale){
 		logger.error(messageSource.getMessage("Error.illegalSortField", new Object[0], locale) + " " + ex.getMessage() + ".", ex);
        ModelAndView mav = new ModelAndView("404");
 		mav.addObject("message",messageSource.getMessage("Error.illegalSortField", new Object[] {ex.getFeild()}, locale) + ".");
 		return mav;
    }
	
}