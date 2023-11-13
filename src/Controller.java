import java.util.List;

import entity.Playlist;
import entity.Usuario;
import service.EmailJaCadastradoException;
import service.LoginInvalidoException;
import service.NomePlaylistJaCadastradaException;
import service.PlaylistService;
import service.UsuarioNaoPossuiIdadeMinimaException;
import service.UsuarioService;
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
	
	public static void main(String[] args) {
		usuarioService = new UsuarioService();
		usuarioView = new UsuarioView();
		playlistView = new PlaylistView();
		playlistService = new PlaylistService();
		
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
			case CADASTRAR:
				controlarCadastroPlaylist();
				break;
			
			case EDITAR:
				controlarEdicaoNomePlaylist();
				
				break;
				
			case VOLTAR:
				return;
			}
		}
		
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
