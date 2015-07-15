package ua.edu.sumdu.j2ee.maxim.laba4.entities;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Entity
@Table(name="category")
public class CategoryLevel implements Serializable {

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

	/*@JoinColumn(name = "pid",referencedColumnName="id")
	@ManyToOne(targetEntity=CategoryLevel.class,
			   cascade=CascadeType.REMOVE)*/
	private CategoryLevel parentCategory;
	
	/*@OneToMany(
			targetEntity = Product.class, 
			mappedBy = "category",
			fetch = LAZY)
	private List<Product>products;*/
	
	@Column(name="LEVEL", nullable=true)
	private int level;
	
	
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
	public CategoryLevel getParentCategory() { 
		return parentCategory; 
	}
	
	
	/*public List<Product> getProducts() {
		return products;
	}*/
	
	
	/**
	 * Method returns level hierarchical.
	 * @return Level hierarchical.
	 */
	public int getLevel() { 
		return level; 
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
	public void setParentCategory(CategoryLevel parentCategory) { 
		this.parentCategory = parentCategory; 
	}
	
	/**
	 * Method returns level hierarchical.
	 * @param level - Level hierarchical.
	 */
	public void setLevel (int level) { 
		this.level = level; 
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(getLevel()).append(" ").append(getId()).append(" ").append(getName()).append(" ")
								  /*.append(getParentCategory().getName())*/.append(" ").toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof CategoryLevel ? 
				(id == ((CategoryLevel) obj).id) : 
				false;
	}
}
