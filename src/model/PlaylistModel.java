package model;

import java.util.List;

import entity.Playlist;
import entity.Usuario;

public class PlaylistModel extends ModelCreateDeleteEditBusca<Playlist> {

	public PlaylistModel() {
		super(Playlist.class);
	}
	
	public boolean conferirSeNomePlaylistJaEstaCadastrada(String nomePlaylist) {
		return buscar()
				.stream()
				.anyMatch(playlist -> playlist.getNome().equals(nomePlaylist));
	}

	public List<Playlist> buscarTodasPlaylistsUsuario(Usuario usuarioLogado) {
		return buscar()
				.stream()
				.filter(playlist -> {
					return (playlist.getUsuario().getId() == usuarioLogado.getId());
				})
				.toList();
	}
	
}
