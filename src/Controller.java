import entity.Usuario;
import service.EmailJaCadastradoException;
import service.LoginInvalidoException;
import service.UsuarioNaoPossuiIdadeMinimaException;
import service.UsuarioService;
import view.UsuarioView;
import view.UsuarioView.OpcaoMenuInicial;

public class Controller {

	public static void main(String[] args) {
		boolean isLoginRealizadoComSucesso = false;
		
		
		while (!isLoginRealizadoComSucesso) {
			UsuarioView usuarioView = new UsuarioView();
			UsuarioService usuarioService = new UsuarioService();
			Usuario usuarioLogado = null;
			
			try {
				OpcaoMenuInicial opcaoMenuInicial = usuarioView.selecionarOpcaoMenuInicial();
				
				switch(opcaoMenuInicial) {
				case ENTRAR:
					usuarioLogado = controlarLogin(usuarioView, usuarioService);		
				case CADASTRAR:
					controlarCadastro(usuarioView, usuarioService);
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
		
		
		
	}
	
	private static Usuario controlarLogin(UsuarioView usuarioView, UsuarioService usuarioService) throws 
		LoginInvalidoException {
		
		String[] dadosLogin = usuarioView.entrar();
		Usuario usuarioLogado = usuarioService.entrar(dadosLogin);
		usuarioView.imprimirMensagemLoginComSucesso();
		return usuarioLogado;
		
	}
	
	private static void controlarCadastro(UsuarioView usuarioView, UsuarioService usuarioService) throws 
		UsuarioNaoPossuiIdadeMinimaException, EmailJaCadastradoException {
		
		Usuario usuario = usuarioView.cadastrar();
		usuarioService.cadastrar(usuario);
		usuarioView.imprimirMensagemCadastroComSucesso();
		
	}

}
