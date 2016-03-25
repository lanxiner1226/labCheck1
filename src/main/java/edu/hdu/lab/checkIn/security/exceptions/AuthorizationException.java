/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.checkIn.security.exceptions;

/**
 *
 * @author justin
 */
public class AuthorizationException 
    extends Exception{
    
	private static final long serialVersionUID = 3733882154319898748L;
	
	public AuthorizationException() {
		super();
	}
	
	public AuthorizationException(String exp) {
		super(exp);
	}    
}
