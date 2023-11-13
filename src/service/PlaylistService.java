package service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import entity.Playlist;
import entity.Usuario;
import model.PlaylistModel;
import model.UsuarioModel;

public class PlaylistService {
	PlaylistModel playlistModel;
	
	public PlaylistService() {
		playlistModel = new PlaylistModel();
	}
	
	public void cadastrar(Playlist playlist) throws NomePlaylistJaCadastradaException {
		
		boolean isPlaylistJaCadastrada = playlistModel
				.conferirSeNomePlaylistJaEstaCadastrada(playlist.getNome());
		
		if (isPlaylistJaCadastrada) {
			throw new NomePlaylistJaCadastradaException();
		}
		
		playlistModel.cadastrar(playlist);

	}
	
	public List<Playlist> buscarTodasPlaylistsUsuario(Usuario usuarioLogado) {
		return playlistModel.buscarTodasPlaylistsUsuario(usuarioLogado);
	}

	public void editarNome(Playlist playlist) throws NomePlaylistJaCadastradaException {
		
		boolean isPlaylistJaCadastrada = playlistModel
				.conferirSeNomePlaylistJaEstaCadastrada(playlist.getNome());
		
		if (isPlaylistJaCadastrada) {
			throw new NomePlaylistJaCadastradaException();
		}
		
		playlistModel.editar(playlist);
	}
	
	public void deletarPlaylist(Playlist playlist) {	
		playlistModel.remover(playlist);
	}
	
}
