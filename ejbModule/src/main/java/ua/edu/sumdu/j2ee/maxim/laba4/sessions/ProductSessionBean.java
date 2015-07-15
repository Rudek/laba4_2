package ua.edu.sumdu.j2ee.maxim.laba4.sessions;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import org.apache.log4j.Logger;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.Category;
import ua.edu.sumdu.j2ee.maxim.laba4.entities.Product;
import ua.edu.sumdu.j2ee.maxim.laba4.exceptions.IllegalSortFieldException;
import ua.edu.sumdu.j2ee.maxim.laba4.interceptors.LoggerBeanInterceptor;


@Stateless(mappedName="SessionsBean")
@Interceptors(LoggerBeanInterceptor.class)
public class ProductSessionBean implements ProductSessionBeanRemote {

	//private static final Logger logger = Logger.getLogger("EJB");
	private static final int countForFirstPage = 6;
	
	@PersistenceContext(unitName = "SessionsBean")
	private EntityManager em;
	private Root<Product> product;

	
	public Long getCountProducts(Long idCat) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<Product> from = criteriaQuery.from(Product.class);
		CriteriaQuery<Long> select = criteriaQuery.select( builder.count(from) );	
		if ( idCat != null ) {
			select.where( builder.equal(from.get("category").get("id"), idCat) );
		}
		Long res = (Long)em.createQuery(select).getSingleResult();
		return res;
		
	}
	
	public List<Product> getAllProducts(String sortBy, String order, Integer first, Integer countEntity) throws IllegalSortFieldException {
		PrepareQueryForExecute prepareQueryForExecute = new PrepareQueryForExecute(sortBy, order, first, countEntity);
		return (List<Product>)prepareQueryForExecute.getQuery().getResultList();
		/*Query query = em.createQuery(sortOrderBy(sortBy, order));
		query.setMaxResults(countEntity);
		query.setFirstResult(first);
		return (List<Product>)query.getResultList();*/
	}

	public List<Product> getProductsByCategoryId(Long idCat, String sortBy, String order, Integer first, Integer countEntity) throws IllegalSortFieldException {
		PrepareQueryForExecute prepareQueryForExecute = new PrepareQueryForExecute(sortBy, order, first, countEntity);
		prepareQueryForExecute.getCriteriaQuery().where(em.getCriteriaBuilder().equal(prepareQueryForExecute.getProduct().get("category").get("id"), idCat));
		return (List<Product>)prepareQueryForExecute.getQuery().getResultList();
		/*CriteriaQuery<Product> criteriaQuery = sortOrderBy(sortBy, order);
		criteriaQuery.where(cb.equal(product.get("category").get("id"), idCat));
		Query query = em.createQuery(criteriaQuery);
		query.setFirstResult(first);
		query.setMaxResults(countEntity);
		return (List<Product>)query.getResultList();*/
	}
	
	private class PrepareQueryForExecute {
		private Integer first;
		private Integer countEntity;
		private CriteriaQuery<Product> criteriaQuery;
		private Root<Product> product;
		private Query query;
		
		public PrepareQueryForExecute(String sortBy, String order, Integer first, Integer countEntity) throws IllegalSortFieldException {
			this.first = first;
			this.countEntity = countEntity;
			CriteriaBuilder crtBuilder = em.getCriteriaBuilder();
			criteriaQuery = crtBuilder.createQuery(Product.class);
			product = criteriaQuery.from(Product.class);
			criteriaQuery.select(product);
			
			Metamodel m = em.getMetamodel();
			ManagedType<Product> Product_ = m.managedType(Product.class);
			
			if ( sortBy != null )  {
				try {
					Product_.getAttribute(sortBy);
				} catch (IllegalArgumentException ex) {
					throw new IllegalSortFieldException(sortBy);
				}
				if ( order != null && "DESC".equals(order = order.toUpperCase()) ) {
					criteriaQuery.orderBy(crtBuilder.desc(product.get(sortBy)));
				} else {
					criteriaQuery.orderBy(crtBuilder.asc(product.get(sortBy)));
				}
			}
			
		}
		
		
		public CriteriaQuery getCriteriaQuery() {
			return criteriaQuery;
		}
		
		public Root<Product> getProduct() {
			return product;
		}
		
		public Query getQuery() {
			query = em.createQuery(criteriaQuery);
			query.setFirstResult(first);
			query.setMaxResults(countEntity);
			return query;
		}
		
	}
	
	
	public Product findProduct(Long id) {
//		logger.info("Find product with id " + id +".");
		return em.find(Product.class,id);
	}
	
	public Long addProduct(Product product) {
		em.persist(product);
		em.flush();
		return product.getId();
	}
	
	public void updateProduct(Product product) {
		Product p = em.find(Product.class,product.getId());
		product.setDateAdd(p.getDateAdd());
		em.merge(product);
	}
	
	public void deleteProduct( Long id ) {
	//	logger.info("Product with id " + "deleted.");
		Product product = em.find(Product.class, id);
		em.remove(product);
	}
	
	public List<Product> getLast6Products() {
		Query query = em.createQuery("SELECT p FROM Product p ORDER BY p.dateAdd DESC",Product.class);
		query.setMaxResults(6);
	    return (List<Product>)query.getResultList();
	}
	

		
}
