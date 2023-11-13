package service;

import entity.Filme;
import entity.Playlist;
import model.FilmeModel;

public class FilmeService {
	FilmeModel filmeModel;
	
	public FilmeService() {
		filmeModel = new FilmeModel();
	}

	public void cadastrar(Filme filme) throws FilmeJaCadastradoPlaylistException {
		
		boolean isFilmeJaCadastrado = filmeModel.buscar()
				.stream()
				.anyMatch((f) -> {
					return (f.getNome().equals(filme.getNome())) &&
							(f.getDataEstreia().toString().equals(filme.getDataEstreia().toString())) &&
							(f.getPlaylist().getId() == filme.getPlaylist().getId());
				});
		
		
		
		if (isFilmeJaCadastrado) {
			throw new FilmeJaCadastradoPlaylistException();
		}
		
		filmeModel.cadastrar(filme);

	}
	
}
