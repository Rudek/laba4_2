package ua.edu.sumdu.j2ee.maxim.laba4.exceptions;
 
public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private Long id;
	
	public ProductNotFoundException(Long id){
        super("ProductNotFoundException with id="+id);
        this.id = id;
    }
	
	public Long getId() {
		return id;
	}
	
	public ProductNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public ProductNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ProductNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
