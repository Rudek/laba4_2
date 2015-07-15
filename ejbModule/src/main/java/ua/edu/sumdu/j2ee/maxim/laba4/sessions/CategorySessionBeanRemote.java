package ua.edu.sumdu.j2ee.maxim.laba4.sessions;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.Category;
import ua.edu.sumdu.j2ee.maxim.laba4.entities.CategoryLevel;


@Remote
public interface CategorySessionBeanRemote {
	public List<Category> getAllCategories();
	public List<Category> getAllCategoriesExceptId(Long id);
	public List<CategoryLevel> getHierarchyCategories();
	public void addCategory(Category category);
	public Category findCategory(Long id);
	public void updateCategory(Category category);
	public void deleteCategory(Long id);
	
}
