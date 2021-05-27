package br.gov.ms.corumba.repositories.exceptions;

public class DuplicateItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DuplicateItemException(String msg) {
		super(msg);
	}

}
