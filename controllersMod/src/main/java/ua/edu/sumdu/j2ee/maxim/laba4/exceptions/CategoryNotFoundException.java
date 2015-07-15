package ua.edu.sumdu.j2ee.maxim.laba4.exceptions;

 
public class CategoryNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private Long id;
	
	public CategoryNotFoundException(Long id){
        super("CategoryNotFoundException with id="+id);
        this.id = id;
    }
	
	public Long getId() {
		return id;
	}
	
	public CategoryNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public CategoryNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CategoryNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CategoryNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
