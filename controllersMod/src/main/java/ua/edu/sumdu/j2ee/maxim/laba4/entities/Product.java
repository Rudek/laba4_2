package ua.edu.sumdu.j2ee.maxim.laba4.entities;

import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="product")
/*@NamedQuery(name="selectLast6Products",
			query="SELECT e FROM (SELECT e FROM product ORDER BY date_add DESC) where ROWNUM < 7")*/
public class Product implements Serializable {

	private static final long serialVersionUID = -4953244457791305915L;

	@Column(name="id")
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQN")
	/*@SequenceGenerator(name = "products_id_seq", 
    sequenceName = "PRODUCT_SEQN",
    allocationSize = 1)*/
	private Long id;
	
	@Column(name="pname")
	@Size(min=2,max=150)
	private String name;
	
	@JoinColumn(name = "cid", referencedColumnName="id")
	@ManyToOne
	//@NotNull
	private Category category;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	@Digits(integer = 8, fraction=2)
	private double price;
	
	@Column(name="image")
	private String image;
	
	/*@Basic(fetch=FetchType.LAZY)
	@Column(name="bimage")
	@Lob
	private String bimage;*/
	
	@Column(name = "date_add")
	@Temporal(DATE)
	private Date dateAdd;

	@PrePersist
	public void prePersist() {
		this.dateAdd = new Date();
	}
	
	/*@PreUpdate
	public void preUpdate() {
		
	}*/
	
	/**
	 * Method return id of products.
	 * @return id of product.
	 */
	public Long getId() { 
		return id; 
	}
	/**
	 * Method return name of product.
	 * @return name of product.
	 */
	public String getName() { 
		return name; 
	}
	/**
	 * Method return category of product.
	 * @return category of product.
	 */
	public Category getCategory() { 
		return category; 
	}
	/**
	 * Method return description of product.
	 * @return description of product.
	 */
	public String getDescription() { 
		return description; 
	}
	/**
	 * Method return price of product.
	 * @return price of product.
	 */
	public double getPrice() { 
		return price; 
	}
	/**
	 * Method return name image for product.
	 * @return name image for product.
	 */
	public String getImage() { 
		return image; 
	}
	
	public Date getDateAdd() {
		return dateAdd;
	}
	
	/*public String getBimage() {
		return bimage;
	}*/
	
	/**
	 * Set identification number of product.
	 * @param id - identification number
	 */
	public void setId(Long id) { 
		this.id = id;
	}
	
	/**
	 * Set name of product.
	 * @param pname - name of product.
	 */
	public void setName(String name) { 
		this.name = name; 
	}
	/**
	 * Set category for product.
	 * @param cid - identification number category.
	 */
	public void setCategory(Category category) { 
		this.category = category; 
	}
	/**
	 * Set description for product.
	 * @param description - description of product.
	 */
	public void setDescription (String description) { 
		this.description = description; 
	}
	/**
	 * Set price for product.
	 * @param price - price for product.
	 */
	public void setPrice(double price) { 
		this.price = price; 
	}
	/**
	 * Set name image for product.
	 * @param image - name image for product.
	 */
	public void setImage ( String image ) { 
		this.image = image; 
	}
	
	public void setDateAdd( Date date ) {
		this.dateAdd = date;
	}
	
	/*public void setBimage(String bimage) {
		this.bimage = bimage;
	}*/
	
	@Override
	public String toString() {
		return getId() + " " + getName() + " " + getDescription() + " " +getCategory() + " " + getImage();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Product ? 
				(id == ((Product) obj).id) : 
				false;
	}
	
}
