package ua.edu.sumdu.j2ee.maxim.laba4.controllers;

import java.beans.PropertyEditorSupport;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.Category;
import ua.edu.sumdu.j2ee.maxim.laba4.entities.Product;
import ua.edu.sumdu.j2ee.maxim.laba4.exceptions.CategoryNotFoundException;
import ua.edu.sumdu.j2ee.maxim.laba4.exceptions.IllegalSortFieldException;
import ua.edu.sumdu.j2ee.maxim.laba4.exceptions.ProductNotFoundException;
import ua.edu.sumdu.j2ee.maxim.laba4.sessions.CategorySessionBeanRemote;
import ua.edu.sumdu.j2ee.maxim.laba4.sessions.ProductSessionBeanRemote;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	
	@Autowired
	private MessageSource messageSource;
	
	@EJB(mappedName="java:app/ejbModule-1.0/ProductSessionBean!ua.edu.sumdu.j2ee.maxim.laba4.sessions.ProductSessionBeanRemote")
	ProductSessionBeanRemote productBeanRemote;
	
	@EJB(mappedName="java:app/ejbModule-1.0/CategorySessionBean!ua.edu.sumdu.j2ee.maxim.laba4.sessions.CategorySessionBeanRemote")
	CategorySessionBeanRemote categoryBeanRemote;
	
	private static final Integer DEFAULT_COUNT_ENTITY = 6;
	
	
    @RequestMapping(value="")
    public ModelAndView allProducts( @RequestParam(value = "page", required=false) Integer page,
							   	     @RequestParam(value = "count", required=false) Integer countEntity,
						    		 @RequestParam(value = "sortby", required=false) String sortBy,				         
									 @RequestParam(value = "order", required=false) String order,
    						   		 HttpServletRequest request) throws IllegalSortFieldException {
    	ModelAndView mav = new ModelAndView("products");
    	
    	countEntity = countEntity == null ? DEFAULT_COUNT_ENTITY : countEntity;
    	page = page == null ? 1 : page;
    	Integer start = page == null ? 0 : countEntity * (page-1);
    	mav.addObject( "countPages",  Math.ceil( new Double( productBeanRemote.getCountProducts(null) )/countEntity) );
    	mav.addObject( "start", start+1 );
    	
    	Map<String,Object> paramsURI = new HashMap<String,Object>();
    	paramsURI.put( "count", countEntity == DEFAULT_COUNT_ENTITY ? null : countEntity);
    	paramsURI.put( "sortby", sortBy);
    	paramsURI.put("order", order);
    	mav.addObject( "paramsURI", paramsURI(paramsURI) );
    	
    	mav.addObject( "products", productBeanRemote.getAllProducts( sortBy, order, start, countEntity ) );
    	return mav;
    }
    
    private String paramsURI(Map<String,Object> params){
    	StringBuilder paramsStr = new StringBuilder();
    	for (Map.Entry<String, Object> param : params.entrySet() ) {
    		if (param.getValue() != null) {
    			paramsStr.append("&").append(param.toString());
    		}
    	}
    	return paramsStr.toString();
    }
    
    @RequestMapping(value="/category/{id}") 
    public ModelAndView showProductsOfCategory(@PathVariable("id") Long id,
											   @RequestParam(value = "page", required=false) Integer page,
									   	       @RequestParam(value = "count", required=false) Integer countEntity,
											   @RequestParam(value = "sortby", required=false) String sortBy,				         
											   @RequestParam(value = "order", required=false) String order,
	    									   Locale locale) throws CategoryNotFoundException, IllegalSortFieldException {
    	Category category = categoryBeanRemote.findCategory(id);
    	if (category == null) {
			throw new CategoryNotFoundException(id);
    	}
    	ModelAndView mav = new ModelAndView("products");
    	countEntity = countEntity == null ? 5 : countEntity;
    	page = page == null ? 1 : page;
    	Integer start = page == null ? 0 : countEntity * (page-1);
    	List<Product> products = productBeanRemote.getProductsByCategoryId(id, sortBy, order, start, countEntity);
    	if ( products.size() == 0 ) {
    		mav.addObject("info", messageSource.getMessage("products.categoryNotFound", new Object[]{category.getName()}, locale));
    	} else {
    	
    		mav.addObject( "countPages",  Math.ceil(new Double(productBeanRemote.getCountProducts(id))/countEntity));
			mav.addObject( "start", start+1 );
			
			Map<String,Object> paramsURI = new HashMap<String,Object>();
			paramsURI.put( "count", countEntity == DEFAULT_COUNT_ENTITY ? null : countEntity);
			paramsURI.put( "sortby", sortBy);
			paramsURI.put("order", order);
			mav.addObject( "paramsURI", paramsURI(paramsURI) );
			
			mav.addObject("products",products);
			mav.addObject("idCat", id);
    	}
    	return mav;
    }
    
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addProduct(Map<String, Object> map) {
		map.put ( "product", new Product() );
	    map.put ( "categories", categoryBeanRemote.getAllCategories());
		return "addProduct";
	}
	
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("id") Long id, 
    						  Map<String, Object> map,
    						  Locale locale) throws ProductNotFoundException {
    	Product product = productBeanRemote.findProduct(id);
    	if (product == null) {
	    	map.put("error", messageSource.getMessage("product.NotFound", new Object[0], locale)  );
			throw new ProductNotFoundException(id);
    	}
		map.put ( "product", product );
    	map.put ( "categories", categoryBeanRemote.getAllCategories());
	    return "addProduct";
    }
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveProduct( @RequestParam(value = "image",required=false) MultipartFile file,
    						   @Validated @ModelAttribute("product")Product product, 
    						   BindingResult result, 
    						   Map<String,Object> map,
    						   final RedirectAttributes redirectAttributes,
    						   HttpServletRequest request,
    						   Locale locale) throws IOException {
    	if ( result.hasErrors() ) {
    		map.put ( "product", product );
    		map.put ( "categories", categoryBeanRemote.getAllCategories());
    		return "addProduct";
    	}
    	
    	String resource = "/WEB-INF/images/upload/";
        URL url =  request.getSession().getServletContext().getResource(resource);
    	if ( !file.isEmpty() ) {
            try {
                Random random = new Random();
                String nameFile = random.nextInt() + "_" + file.getOriginalFilename();
                product.setImage(nameFile);
                
                File serverFile = new File(url.getPath()+"/"+nameFile);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();
            } catch (IOException e) {
            	redirectAttributes.addFlashAttribute("error",messageSource.getMessage("Error.image", new Object[0], locale)+" " + e.getMessage());
            	return "redirect:/product/edit/"+product.getId();
            }
        }
    	
    	Long id = product.getId();
    	if ( id != null) {
    		File oldImage = new File(url.getPath() +"/" + productBeanRemote.findProduct(product.getId()).getImage() );
    		oldImage.delete();
    		productBeanRemote.updateProduct(product);
    		redirectAttributes.addFlashAttribute("info",messageSource.getMessage("product.successUpdate", new Object[0], locale));
    	} else {
    		id = productBeanRemote.addProduct(product);
    		redirectAttributes.addFlashAttribute("info",messageSource.getMessage("product.successAdd", new Object[0], locale));
    	}
    	return "redirect:/product/show/" + id;
    }
    
    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) throws Exception {
    	binder.setAllowedFields("id","name","category","description","price");
    	binder.registerCustomEditor(Category.class, "category", new PropertyEditorSupport(Category.class) {
    		@Override
    		public void setAsText(String cid) {
    			Category category = categoryBeanRemote.findCategory(Long.parseLong(cid));
    			setValue(category);
    		}
    	});
    }
    

    
    @RequestMapping(value="/show/{id}")
    public String showProduct ( @PathVariable("id") Long id, Map<String, Object> map) throws ProductNotFoundException {
    	Product product = productBeanRemote.findProduct(id);
    	if (product == null) {
    		//map.put("error", messageSource.getMessage("product.NotFound", new Object[0], locale)  );
			throw new ProductNotFoundException(id);
    	}
    	map.put("title", product.getName());
    	map.put("product", product);	
        return "showProduct";
    }

    @RequestMapping(value="/delete/{id}")
    public String deleteProduct( @PathVariable("id") Long id, 
    							 Map<String,Object> map,
    							 final RedirectAttributes redirectAttributes,
      						   	 Locale locale) throws ProductNotFoundException {
    	if ( productBeanRemote.findProduct(id) == null) {
    		throw new ProductNotFoundException();
    	}
    	productBeanRemote.deleteProduct(id);
    	redirectAttributes.addFlashAttribute("info", messageSource.getMessage("product.remove", new Object[0], locale));
    	return "redirect:/product";
    }

}
