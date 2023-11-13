package model;

import java.util.List;

import entity.Filme;
import entity.Playlist;

public class FilmeModel extends ModelCreateDeleteEditBusca<Filme> {

	public FilmeModel() {
		super(Filme.class);
	}
	
	public List<Filme> buscarTodosFilmesPlaylist(Playlist playlist) {
		return buscar()
				.stream()
				.filter(filme -> filme.getPlaylist().getId() == playlist.getId())
				.toList();
	}

}
