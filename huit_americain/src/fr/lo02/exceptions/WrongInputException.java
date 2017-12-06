package fr.lo02.exceptions;

import java.io.IOException;

/**
 * Exception envoyée lorsque l'utilisateur entre entrée non attendue.
 * @author Lionel EA
 *
 */
public class WrongInputException extends IOException {

	public WrongInputException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WrongInputException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public WrongInputException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public WrongInputException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
