import java.util.List;

import entity.Filme;
import entity.Playlist;
import entity.Usuario;
import service.EmailJaCadastradoException;
import service.FilmeJaCadastradoPlaylistException;
import service.FilmeService;
import service.LoginInvalidoException;
import service.NomePlaylistJaCadastradaException;
import service.PlaylistService;
import service.UsuarioNaoPossuiIdadeMinimaException;
import service.UsuarioService;
import view.FilmeView;
import view.PlaylistView;
import view.PlaylistView.OpcaoMenuInicialPlaylist;
import view.UsuarioView;
import view.UsuarioView.OpcaoMenuDashboard;
import view.UsuarioView.OpcaoMenuInicial;
import view.UsuarioView.OpcaoMenuPaginaUsuario;

public class Controller {
	private static UsuarioView usuarioView;
	private static UsuarioService usuarioService;
	private static Usuario usuarioLogado;
	private static PlaylistView playlistView;
	private static PlaylistService playlistService;
	private static FilmeView filmeView;
	private static FilmeService filmeService;
	
	public static void main(String[] args) {
		usuarioService = new UsuarioService();
		usuarioView = new UsuarioView();
		playlistView = new PlaylistView();
		playlistService = new PlaylistService();
		filmeView = new FilmeView();
		filmeService = new FilmeService();
		
		while (true) {
			usuarioLogado = controlarLoginOuCadastro();
		
			controlarDashboardUsuarioLogado();
		}
		
	}

	private static void controlarDashboardUsuarioLogado() {
		while (true) {
			OpcaoMenuDashboard opcaoMenuDashboard = usuarioView.selecionarOpcaoDashboard();
			switch (opcaoMenuDashboard) {
			case PAGINA_DO_USUARIO:
				OpcaoMenuPaginaUsuario opcaoPaginaUsuarioSelecionada = controlarPaginaUsuario();
				if (opcaoPaginaUsuarioSelecionada.equals(OpcaoMenuPaginaUsuario.EXCLUIR_CONTA)) {
					return;
				}
				break;
				
			case MINHAS_PLAYLISTS:
				controlarPaginaInicialPlaylist();
				break;
			
			case SAIR:
				usuarioLogado = null;
				return;
			}
		}
	}
	
	public static void controlarPaginaInicialPlaylist() {
		
		while (true) {
			List<Playlist> todasPlaylistsUsuario = playlistService.buscarTodasPlaylistsUsuario(usuarioLogado);
			playlistView.imprimirPlaylists(todasPlaylistsUsuario);
			
			OpcaoMenuInicialPlaylist opcaoMenuInicialPlaylist = 
					playlistView.selecionarOpcaoMenuInicialPlaylist();
			
			switch (opcaoMenuInicialPlaylist) {
			case CADASTRAR_PLAYLIST:
				controlarCadastroPlaylist();
				break;
			
			case EDITAR_PLAYLIST:
				controlarEdicaoNomePlaylist();
				break;
				
			case DELETAR_PLAYLIST:
				controlarExclusaoPlaylist();
				break;
				
			case CADASTRAR_FILME:
				controlarCadastroFilme();
				break;
				
			case EDITAR_FILME:
				controlarEdicaoFilme();
				break;
				
			case DELETAR_FILME:
				controlarDelecaoFilme();
				break;
				
			case VISUALIZAR_FILME:
				controlarVisualizacaoFilme();
				break;
				
			case VOLTAR:
				return;
			}
		}
		
	}
	
	public static void controlarVisualizacaoFilme() {
		List<Playlist> playlistsUsuario = playlistService.buscarTodasPlaylistsUsuario(usuarioLogado);
		Playlist playlistFilme;
		
		if (playlistsUsuario.isEmpty()) {
			playlistView.imprimirMensagemNaoHaPlaylist();
			return;
		}
		
		try {
			playlistFilme = playlistView.selecionarPlaylist(playlistsUsuario);
		} catch (Exception e) {
			playlistView.imprimirMensagemNaoHaIdPlaylistSelecionado();
			return;
		}
		
		List<Filme> filmesPlaylist = filmeService.buscarTodosFilmesPlaylist(playlistFilme);
		
		if (filmesPlaylist.isEmpty()) {
			filmeView.imprimirMensagemNaoHaFilme();
			return;
		}
		
		filmeView.imprimirFilmes(filmesPlaylist);
		
		
	}
	
	public static void controlarDelecaoFilme() {
		List<Playlist> playlistsUsuario = playlistService.buscarTodasPlaylistsUsuario(usuarioLogado);
		Playlist playlistParaDeletarFilme;
		Filme filmeParaDeletar;
		
		if (playlistsUsuario.isEmpty()) {
			playlistView.imprimirMensagemNaoHaPlaylist();
			return;
		}
		
		try {
			playlistParaDeletarFilme = playlistView.selecionarPlaylist(playlistsUsuario);
		} catch (Exception e) {
			playlistView.imprimirMensagemNaoHaIdPlaylistSelecionado();
			return;
		}
		
		List<Filme> filmesPlaylist = filmeService.buscarTodosFilmesPlaylist(playlistParaDeletarFilme);
		
		if (filmesPlaylist.isEmpty()) {
			filmeView.imprimirMensagemNaoHaFilme();
			return;
		}
		
		filmeView.imprimirFilmes(filmesPlaylist);
		
		try {
			filmeParaDeletar = filmeView.selecionarFilme(filmesPlaylist);
		} catch (Exception e) {
			playlistView.imprimirMensagemNaoHaIdPlaylistSelecionado();
			return;
		}
			
		filmeService.remover(filmeParaDeletar);
		filmeView.imprimirMensagemDelecaoComSucesso();
		return;
	}
	
	public static void controlarEdicaoFilme() {
		List<Playlist> playlistsUsuario = playlistService.buscarTodasPlaylistsUsuario(usuarioLogado);
		Playlist playlistParaEditarFilme;
		Filme filmeParaEditar;
		
		if (playlistsUsuario.isEmpty()) {
			playlistView.imprimirMensagemNaoHaPlaylist();
			return;
		}
		
		try {
			playlistParaEditarFilme = playlistView.selecionarPlaylist(playlistsUsuario);
		} catch (Exception e) {
			playlistView.imprimirMensagemNaoHaIdPlaylistSelecionado();
			return;
		}
		
		List<Filme> filmesPlaylist = filmeService.buscarTodosFilmesPlaylist(playlistParaEditarFilme);
		
		if (filmesPlaylist.isEmpty()) {
			filmeView.imprimirMensagemNaoHaFilme();
			return;
		}
		
		filmeView.imprimirFilmes(filmesPlaylist);
		
		try {
			filmeParaEditar = filmeView.selecionarFilme(filmesPlaylist);
		} catch (Exception e) {
			playlistView.imprimirMensagemNaoHaIdPlaylistSelecionado();
			return;
		}
			
		
		while (true) {
			filmeView.editar(filmeParaEditar);
			try {
				filmeService.editar(filmeParaEditar);
				filmeView.imprimirMensagemEdicaoComSucesso();
				return;
			} catch (FilmeJaCadastradoPlaylistException e) {
				filmeView.imprimirMensagemFilmeJaCadastrado();
			}
		}
	}
	
	public static void controlarCadastroFilme() {
		List<Playlist> playlistsUsuario = playlistService.buscarTodasPlaylistsUsuario(usuarioLogado);
		Playlist playlistParaCadastrarFilme;
		
		if (playlistsUsuario.isEmpty()) {
			playlistView.imprimirMensagemNaoHaPlaylist();
			return;
		}
		
		try {
			playlistParaCadastrarFilme = playlistView.selecionarPlaylist(playlistsUsuario);
		} catch (Exception e) {
			playlistView.imprimirMensagemNaoHaIdPlaylistSelecionado();
			return;
		}
		
		Filme filme = filmeView.cadastrar(playlistParaCadastrarFilme);
		
		try {
			filmeService.cadastrar(filme);
			filmeView.imprimirMensagemCadastroComSucesso();
		} catch (FilmeJaCadastradoPlaylistException e) {
			filmeView.imprimirMensagemFilmeJaCadastrado();
		}
	}
	
	public static void controlarExclusaoPlaylist() {
		List<Playlist> playlistsUsuario = playlistService.buscarTodasPlaylistsUsuario(usuarioLogado);
		Playlist playlistParaExcluir;
		
		if (playlistsUsuario.isEmpty()) {
			playlistView.imprimirMensagemNaoHaPlaylistParaExcluir();
			return;
		}
		
		try {
			playlistParaExcluir = playlistView.selecionarPlaylistExclusao(playlistsUsuario);
		} catch (Exception e) {
			playlistView.imprimirMensagemNaoHaIdPlaylistSelecionado();
			return;
		}

		playlistService.deletarPlaylist(playlistParaExcluir);
	}
	
	public static void controlarEdicaoNomePlaylist() {
		List<Playlist> playlistsUsuario = playlistService.buscarTodasPlaylistsUsuario(usuarioLogado);
		Playlist playlistParaAlterar;
		
		if (playlistsUsuario.isEmpty()) {
			playlistView.imprimirMensagemNaoHaPlaylistParaEdicao();
			return;
		}
		
		try {
			playlistParaAlterar = playlistView.selecionarPlaylistEdicao(playlistsUsuario);
		} catch (Exception e) {
			playlistView.imprimirMensagemNaoHaIdPlaylistSelecionado();
			return;
		}
		
		while (true) {
			try {
				playlistView.editarNome(playlistParaAlterar);
				playlistService.editarNome(playlistParaAlterar);
				playlistView.imprimirMensagemNomeEditadaComSucesso();
				break;
			} catch (NomePlaylistJaCadastradaException e) {
				playlistView.imprimirMensagemPlaylistJaCadastrada();
			}
		}
	}
	
	public static void controlarCadastroPlaylist() {
		while (true) {
			try {
				Playlist usuario = playlistView.cadastrar(usuarioLogado);
				playlistService.cadastrar(usuario);
				break;
			} catch(NomePlaylistJaCadastradaException e) {
				playlistView.imprimirMensagemPlaylistJaCadastrada();
			}
		}
		playlistView.imprimirMensagemCadastroComSucesso();

	}
	
	public static OpcaoMenuPaginaUsuario controlarPaginaUsuario() {
		OpcaoMenuPaginaUsuario opcaoMenuPaginaUsuario = usuarioView.selecionarOpcaoMenuPaginaUsuario();
		
		switch (opcaoMenuPaginaUsuario) {
		case VISUALIZAR_TODOS_USUARIOS:
			List<Usuario> todosUsuarios = usuarioService.buscarTodosUsuarios();
			usuarioView.imprimirUsuarios(todosUsuarios);
			break;
			
		case EXCLUIR_CONTA:
			usuarioService.deletarConta(usuarioLogado);
			usuarioLogado = null;
			break;
		
		case EDITAR_SENHA:
			usuarioView.editarSenha(usuarioLogado);
			usuarioService.editarSenha(usuarioLogado);
			usuarioView.imprimirMensagemSenhaEditadaComSucesso();
			break;
			
		case VOLTAR:
			break;
		}
		
		return opcaoMenuPaginaUsuario;
	}
	
	private static Usuario controlarLoginOuCadastro() {
		Usuario usuarioLogado = null;
		boolean isLoginRealizadoComSucesso = false;
		
		while (!isLoginRealizadoComSucesso) {
			try {
				OpcaoMenuInicial opcaoMenuInicial = usuarioView.selecionarOpcaoMenuInicial();
				
				switch(opcaoMenuInicial) {
				case ENTRAR:
					usuarioLogado = controlarLogin();	
					break;
				case CADASTRAR:
					controlarCadastro();
					break;
				}
					
				isLoginRealizadoComSucesso = usuarioLogado != null;
			} catch (UsuarioNaoPossuiIdadeMinimaException e) {
				usuarioView.imprimirMensagemUsuarioNaoPossuiIdadeMinima();
			} catch (EmailJaCadastradoException e) {
				usuarioView.imprimirMensagemEmailJaCadastrado();
			} catch (LoginInvalidoException e) {
				usuarioView.imprimirMensagemLoginInvalido();
			}
		}
		return usuarioLogado;
	}
	
	private static Usuario controlarLogin() throws 
		LoginInvalidoException {
		
		String[] dadosLogin = usuarioView.entrar();
		Usuario usuarioLogado = usuarioService.entrar(dadosLogin);
		usuarioView.imprimirMensagemLoginComSucesso();
		return usuarioLogado;
		
	}
	
	private static void controlarCadastro() throws 
		UsuarioNaoPossuiIdadeMinimaException, EmailJaCadastradoException {
		
		Usuario usuario = usuarioView.cadastrar();
		usuarioService.cadastrar(usuario);
		usuarioView.imprimirMensagemCadastroComSucesso();
		
	}

}
