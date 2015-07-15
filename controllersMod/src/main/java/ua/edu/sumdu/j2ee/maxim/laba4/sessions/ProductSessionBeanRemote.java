package ua.edu.sumdu.j2ee.maxim.laba4.sessions;

import java.util.List;

import javax.ejb.Remote;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.Product;
import ua.edu.sumdu.j2ee.maxim.laba4.exceptions.IllegalSortFieldException;


@Remote
public interface ProductSessionBeanRemote {
	public List<Product> getAllProducts(String sortBy, String order, Integer page, Integer maxResult) throws IllegalSortFieldException;
	public Long getCountProducts(Long idCat);
	public Long addProduct(Product product);
	public Product findProduct(Long id);
	public List<Product> getProductsByCategoryId(Long idCat, String sortBy, String order, Integer page, Integer countEntity) throws IllegalSortFieldException;
	public void updateProduct(Product product);
	public void deleteProduct( Long id );
	public List<Product> getLast6Products();
}
