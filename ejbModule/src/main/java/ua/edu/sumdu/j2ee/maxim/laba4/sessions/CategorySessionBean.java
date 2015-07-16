package ua.edu.sumdu.j2ee.maxim.laba4.sessions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.Category;
import ua.edu.sumdu.j2ee.maxim.laba4.entities.CategoryLevel;
import ua.edu.sumdu.j2ee.maxim.laba4.interceptors.LoggerBeanInterceptor;

@Stateless(mappedName="SessionsBean")
@Interceptors(LoggerBeanInterceptor.class)
public class CategorySessionBean implements CategorySessionBeanRemote {

	private static final String GET_LEVEL_ID_NAME_PID_CATS = "SELECT LEVEL, id, cname, pid FROM category START WITH pid IS NULL CONNECT BY PRIOR id = pid";
	
	@PersistenceContext(unitName = "SessionsBean")
	private EntityManager em;

	public List<CategoryLevel> getHierarchyCategories() {
		Query query = em.createNativeQuery(GET_LEVEL_ID_NAME_PID_CATS, CategoryLevel.class);
		return (List<CategoryLevel>)query.getResultList();
	}
	
	public List<Category> getAllCategories() {
		return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
	}
	
	public List<Category> getAllCategoriesExceptId(Long id) {
		return em.createQuery("SELECT c FROM Category c WHERE c.id<>:id", Category.class).setParameter("id", id).getResultList();
	}
	
	public Category findCategory(Long id) {
		return em.find(Category.class,id);
	}
		
	public void updateCategory(Category category) {
		em.merge(category);
	}
	
	public void addCategory(Category category) {
		em.persist(category);
	}
	
	public void deleteCategory(Long id) {
		Category category = em.find(Category.class, id);
		em.remove(category);
	}	
}
