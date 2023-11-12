import entity.Usuario;
import service.EmailJaCadastradoException;
import service.UsuarioNaoPossuiIdadeMinimaException;
import service.UsuarioService;
import view.UsuarioView;
import view.UsuarioView.OpcaoMenuInicial;

public class Controller {

	public static void main(String[] args) {
		boolean usuarioEntrouComSucesso = false;
		
		
		while (!usuarioEntrouComSucesso) {
			UsuarioView usuarioView = new UsuarioView();
			UsuarioService usuarioService = new UsuarioService();
			
			try {
				OpcaoMenuInicial opcaoMenuInicial = usuarioView.selecionarOpcaoMenuInicial();
				
				switch(opcaoMenuInicial) {
				case ENTRAR:
					String[] dadosLogin = usuarioView.entrar();
					
				case CADASTRAR:
					controlarCadastro(usuarioView, usuarioService);
				}
				
				usuarioEntrouComSucesso = true;
			} catch (UsuarioNaoPossuiIdadeMinimaException e) {
				usuarioView.imprimirMensagemUsuarioNaoPossuiIdadeMinima();
			} catch (EmailJaCadastradoException e) {
				usuarioView.imprimirMensagemEmailJaCadastrado();
			}
		
		}
		
	}
	
	private static void controlarCadastro(UsuarioView usuarioView, UsuarioService usuarioService) throws 
	UsuarioNaoPossuiIdadeMinimaException, EmailJaCadastradoException {
		Usuario usuario = usuarioView.cadastrar();
		usuarioService.cadastrar(usuario);
	}

}
