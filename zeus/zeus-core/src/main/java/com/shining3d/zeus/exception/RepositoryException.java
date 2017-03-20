package com.shining3d.zeus.exception;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = -605576641698057067L;

	public RepositoryException() {
        super();
    }


    public  RepositoryException(String message) {
        super(message);
    }
	

    public RepositoryException(Throwable exception) {
        super(exception);
    }
    

    public RepositoryException(String message, Throwable exception) {
        super(message, exception);
    }

}
