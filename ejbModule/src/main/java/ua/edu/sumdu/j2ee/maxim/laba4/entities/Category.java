package ua.edu.sumdu.j2ee.maxim.laba4.entities;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name="category")
public class Category implements Serializable {

	private static final long serialVersionUID = -1575253191761374534L;
	
	@Id
	@Column(name="id")
	/*@SequenceGenerator(name = "cat_seqn", 
    				     sequenceName = "CAT_SEQN",
    				     allocationSize = 1)*/ 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAT_SEQN")
	private Long id;
	
	@Column(name="cname",unique=true)
	@Size(min=2,max=150)
	private String name;

	@JoinColumn(name = "pid",referencedColumnName="id")
	@ManyToOne(/*targetEntity=Category.class,
			   cascade=CascadeType.REMOVE*/)
	private Category parentCategory;
	
	@OneToMany( targetEntity = Product.class, 
			    mappedBy = "category",
			    fetch=FetchType.EAGER)
	private List<Product>products;
	
	
	
	/**
	 * Method returns category's identification number.
	 * @return Category's identification number.
	 */
	public Long getId() { 
		return id; 
	}
	
	/**
	 * Method returns name of category.
	 * @return Category's name.
	 */
	public String getName() { 
		return name; 
	}
	
	/**
	 * Method returns parent identification number.
	 * @return Parent identification number.
	 */
	public Category getParentCategory() { 
		return parentCategory; 
	}
	
	
	public List<Product> getProducts() {
		return products;
	}
	
	
	/**
	 * Method sets identification number.
	 * @param id - identification number.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Method sets category's name.
	 * @param cname - category's name.
	 */
	public void setName(String name) {
		this.name = name; 
	}
	
	
	/**
	 * Method sets parent category's identification number.
	 * @param pid - parent category's identification number.
	 */
	public void setParentCategory(Category parentCategory) { 
		this.parentCategory = parentCategory; 
	}

	/**
	 * Method sets list products for current category.
	 * @param products - list of products.
	 */
	public void setProducts(List<Product> products){
		this.products = products;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(getId()).append(" ").append(getName()).append(" ")
								  /*.append(getParentCategory().getName())*/.append(" ").toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Category ? 
				(id == ((Category) obj).id) : 
				false;
	}
}
