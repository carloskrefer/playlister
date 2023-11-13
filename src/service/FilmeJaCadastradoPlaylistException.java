package service;

public class FilmeJaCadastradoPlaylistException extends Exception {

	public FilmeJaCadastradoPlaylistException() {
		super("O filme já foi cadastrado nesta playlist.");
	}

}
