package model;

import entity.Playlist;

public class PlaylistModel extends ModelCreateDeleteEditBusca<Playlist> {

	public PlaylistModel() {
		super(Playlist.class);
	}
	
	public boolean conferirSeNomePlaylistJaEstaCadastrada(String nomePlaylist) {
		return buscar()
				.stream()
				.anyMatch(playlist -> playlist.getNome().equals(nomePlaylist));
	}

}
