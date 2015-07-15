package ua.edu.sumdu.j2ee.maxim.laba4.exceptions;

 
public class IllegalSortFieldException extends Exception {

	private static final long serialVersionUID = 1L;
	private String field;
	
	public IllegalSortFieldException(String field){
        super("Field \""+field+"\" is absent for sorting.");
        this.field = field;
    }
	
	public String getFeild() {
		return field;
	}
	
	public IllegalSortFieldException() {
	}


	public IllegalSortFieldException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalSortFieldException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
