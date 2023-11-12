package service;

public class LoginInvalidoException extends Exception {

	public LoginInvalidoException() {
		super("O e-mail informado já está cadastrado.");
	}

}
