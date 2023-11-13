package view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import entity.Playlist;
import entity.Usuario;
import view.UsuarioView.OpcaoMenuDashboard;

public class PlaylistView extends View {

	public enum OpcaoMenuInicialPlaylist {
		CADASTRAR, VISUALIZAR
	}
	
	public PlaylistView() {
		super();
	}
	
	public OpcaoMenuInicialPlaylist selecionarOpcaoMenuInicialPlaylist() {
		imprimirTitulo("Página das playlists");

		exibirOpcoes(OpcaoMenuInicialPlaylist.values());
		
		return selecionarOpcao(OpcaoMenuInicialPlaylist.values());
	}
	
	public Playlist cadastrar(Usuario usuarioDonoPlaylist) {
		imprimirTitulo("Cadastro de playlist");
		
		System.out.println("Informe o nome da playlist: ");
		String nomePlaylist = preencherCampoTexto(255, false);

		LocalDateTime dataCriacao = LocalDateTime.now();
			
		Playlist playlist = new Playlist(nomePlaylist, dataCriacao, usuarioDonoPlaylist);
		
		return playlist;
	}
	
	public void imprimirMensagemCadastroComSucesso() {
		imprimirTitulo("Sucesso");
		
		System.out.println("Cadastro de playlis realizado com sucesso!\n");
	}
	
	public void imprimirMensagemPlaylistJaCadastrada() {
		imprimirTitulo("Erro");
		
		System.out.println("Desculpe, esta playlist já está cadastrado.\n");
	}
	

}
