package service;

public class NomePlaylistJaCadastradaException extends Exception {

	public NomePlaylistJaCadastradaException() {
		super("Uma playlist com este nome já foi cadastrada.");
	}

}
