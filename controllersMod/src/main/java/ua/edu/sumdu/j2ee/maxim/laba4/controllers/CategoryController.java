package ua.edu.sumdu.j2ee.maxim.laba4.controllers;

import java.beans.PropertyEditorSupport;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.Category;
import ua.edu.sumdu.j2ee.maxim.laba4.exceptions.CategoryNotFoundException;
import ua.edu.sumdu.j2ee.maxim.laba4.sessions.CategorySessionBeanRemote;
import ua.edu.sumdu.j2ee.maxim.laba4.sessions.ProductSessionBeanRemote;

@Controller
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired
	private MessageSource messageSource;
	
	@EJB(mappedName="java:app/ejbModule-1.0/ProductSessionBean!ua.edu.sumdu.j2ee.maxim.laba4.sessions.ProductSessionBeanRemote")
	ProductSessionBeanRemote productBeanRemote;
	
	@EJB(mappedName="java:app/ejbModule-1.0/CategorySessionBean!ua.edu.sumdu.j2ee.maxim.laba4.sessions.CategorySessionBeanRemote")
	CategorySessionBeanRemote categoryBeanRemote;
	
    @RequestMapping(value="")
    public String allCategories(Map<String, Object> map, Locale locale) {
    	map.put("categories",categoryBeanRemote.getAllCategories());
    	map.put ( "title", messageSource.getMessage("category.category", new Object[0], locale) );
    	return "categories";
    }

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addCategory(Map<String, Object> map, Locale locale) {
		map.put ( "title", messageSource.getMessage("category.add", new Object[0], locale) );
		map.put ( "category", new Category() );
	    map.put ( "categories", categoryBeanRemote.getAllCategories());
		return "addCategory";
	}
	
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String editCategory(@PathVariable("id") Long id, 
    						   Map<String, Object> map,
    						   Locale locale) throws CategoryNotFoundException {
    	
    	Category category = categoryBeanRemote.findCategory(id);
    	if (category == null) {
	    	map.put("error", messageSource.getMessage("category.NotFound", new Object[0], locale) );
			throw new CategoryNotFoundException(id);
    	}
    	map.put ( "title", messageSource.getMessage("category.update", new Object[0], locale) );
    	map.put ( "category", category );
    	map.put ( "categories", categoryBeanRemote.getAllCategoriesExceptId(id));
	    return "addCategory";
    }
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveCategory(@Valid @ModelAttribute("category")Category category, 
    							BindingResult result, 
    							final RedirectAttributes redirectAttributes,
    							Locale locale) {
    	
    	if ( result.hasErrors() ) {
    		return "addCategory";
    	}
    	
    	Long id = category.getId();
    	if ( id != null) {
    		categoryBeanRemote.updateCategory(category);
    		redirectAttributes.addFlashAttribute("info",messageSource.getMessage("category.successUpdate", new Object[0], locale));
    	} else {
    		categoryBeanRemote.addCategory(category);
    		redirectAttributes.addFlashAttribute("info",messageSource.getMessage("category.successAdd", new Object[0], locale));
    	}
    	return "redirect:/category";
    }
    
    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws Exception {
    	//binder.setAllowedFields("id","name","category","description","price");
    	binder.registerCustomEditor(Category.class, "parentCategory", new PropertyEditorSupport(Category.class) {
    		@Override
    		public void setAsText(String id) {
    			//System.out.println("\n " + id + "\n");
    			Category category = categoryBeanRemote.findCategory(Long.parseLong(id));
    			setValue(category);
    		}
    		/*@Override
    		public String getAsText() {
    			Category category = (Category)getValue();
    			return Long.toString(category.getId());
    		}*/
    	});
    }


    
    @RequestMapping(value="/delete/{id}")
    public String deleteCategory( @PathVariable("id") Long id, Map<String,Object> map ) throws CategoryNotFoundException {
    	if (categoryBeanRemote.findCategory(id) == null) {
			throw new CategoryNotFoundException(id);
    	}
    	categoryBeanRemote.deleteCategory(id);
    	return "redirect:/category";
    }

}
