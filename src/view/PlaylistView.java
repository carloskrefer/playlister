package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import entity.Playlist;
import entity.Usuario;
import view.UsuarioView.OpcaoMenuDashboard;

public class PlaylistView extends View {

	public enum OpcaoMenuInicialPlaylist {
		CADASTRAR, EDITAR, VOLTAR
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
	
	public void editarNome(Playlist playlist) {
		imprimirTitulo("EDITAR NOME DE PLAYLIST");
		
		System.out.println("Informe o novo nome: ");
		String novoNome = preencherCampoTexto(255, false);
		
		playlist.setNome(novoNome);
	}
	
	public void imprimirMensagemCadastroComSucesso() {
		imprimirTitulo("Sucesso");
		
		System.out.println("Cadastro de playlist realizado com sucesso!\n");
	}
	
	public void imprimirMensagemPlaylistJaCadastrada() {
		imprimirTitulo("Erro");
		
		System.out.println("Desculpe, esta playlist já está cadastrada.\n");
	}
	
	public void imprimirMensagemNomeEditadaComSucesso() {
		imprimirTitulo("Sucesso");
		
		System.out.println("O nome da playlist foi editado com sucesso!\n");
	}
	
	public void imprimirMensagemNaoHaPlaylistParaEdicao() {
		imprimirTitulo("Erro");
		
		System.out.println("Não há playlists cadastradas para edição.\n");
	}
	
	public void imprimirMensagemNaoHaIdPlaylistSelecionado() {
		imprimirTitulo("Erro");
		
		System.out.println("Não playlist para o ID selecionado.\n");
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
	
	public Playlist selecionarPlaylistEdicao(List<Playlist> playlistsUsuario) {
		imprimirTitulo("Seleção de ID da playlist para editar");
		
		System.out.println("Informe o ID da playlist: ");
		int idPlaylist = preencherInteiroID();
		
		return playlistsUsuario.stream().filter((playlist) -> 
					playlist.getId() == idPlaylist
				).findFirst().get();	
		
	}
	

}
