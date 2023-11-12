import java.util.List;

import entity.Usuario;
import service.EmailJaCadastradoException;
import service.LoginInvalidoException;
import service.UsuarioNaoPossuiIdadeMinimaException;
import service.UsuarioService;
import view.UsuarioView;
import view.UsuarioView.OpcaoMenuDashboard;
import view.UsuarioView.OpcaoMenuInicial;
import view.UsuarioView.OpcaoMenuPaginaUsuario;

public class Controller {
	private static UsuarioView usuarioView;
	private static UsuarioService usuarioService;
	private static Usuario usuarioLogado;
	
	public static void main(String[] args) {
		usuarioService = new UsuarioService();
		usuarioView = new UsuarioView();
		
		usuarioLogado = controlarLoginOuCadastro();
		
		controlarDashboardUsuarioLogado();
		
	}

	private static void controlarDashboardUsuarioLogado() {
		OpcaoMenuDashboard opcaoMenuDashboard = usuarioView.selecionarOpcaoDashboard();
		
		switch (opcaoMenuDashboard) {
		case PAGINA_DO_USUARIO:
			controlarPaginaUsuario();
			break;

		default:
			break;
		}
	}
	
	public static void controlarPaginaUsuario() {
		OpcaoMenuPaginaUsuario opcaoMenuPaginaUsuario = usuarioView.selecionarOpcaoMenuPaginaUsuario();
		
		switch (opcaoMenuPaginaUsuario) {
		case VISUALIZAR_TODOS_USUARIOS:
			List<Usuario> todosUsuarios = usuarioService.buscarTodosUsuarios();
			usuarioView.imprimirUsuarios(todosUsuarios);
			break;
		case EXCLUIR_CONTA:
			usuarioService.deletarConta(usuarioLogado);
		}
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
