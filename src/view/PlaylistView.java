package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import entity.Playlist;
import entity.Usuario;
import view.UsuarioView.OpcaoMenuDashboard;

public class PlaylistView extends View {

	public enum OpcaoMenuInicialPlaylist {
		CADASTRAR, VISUALIZAR, VOLTAR
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
		
		System.out.println("Cadastro de playlist realizado com sucesso!\n");
	}
	
	public void imprimirMensagemPlaylistJaCadastrada() {
		imprimirTitulo("Erro");
		
		System.out.println("Desculpe, esta playlist já está cadastrada.\n");
	}
	
	public void imprimirPlaylists(List<Playlist> playlists) {
		imprimirTitulo("Lista de playlists");
		
		System.out.println("Confira o ID de todas suas playlists, seus nomes e datas de criação.\n");
		
		playlists.stream().forEach((playlist) -> {
			System.out.println(
					playlist.getId() + " \t" + 
					playlist.getNome() + " \t" + 
					playlist.getDataHoraCriacao()
			);
		});
		
		System.out.println();
	}
	

}
