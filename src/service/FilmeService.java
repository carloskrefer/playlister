package service;

import java.util.List;

import entity.Filme;
import entity.Playlist;
import entity.Usuario;
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
	
	public void editar(Filme filme) throws FilmeJaCadastradoPlaylistException {
		
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
		
		filmeModel.editar(filme);

	}
	
	public void remover(Filme filme) {

		filmeModel.remover(filme);

	}
	
	public List<Filme> buscarTodosFilmesPlaylist(Playlist playlist) {
		return filmeModel.buscarTodosFilmesPlaylist(playlist);
	}
	
}
