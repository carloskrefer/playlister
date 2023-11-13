package view;

import java.time.LocalDate;
import java.util.List;

import entity.Usuario;

public class UsuarioView extends View {
	
	public enum OpcaoMenuInicial {
		ENTRAR, CADASTRAR
	}
	
	public enum OpcaoMenuDashboard {
		PAGINA_DO_USUARIO, MINHAS_PLAYLISTS, SAIR
	}
	
	public enum OpcaoMenuPaginaUsuario {
		VISUALIZAR_TODOS_USUARIOS, VOLTAR, EXCLUIR_CONTA, EDITAR_SENHA
	}
	
	public UsuarioView() {
		super();
	}
	
	public OpcaoMenuDashboard selecionarOpcaoDashboard() {
		imprimirTitulo("Dashboard");

		exibirOpcoes(OpcaoMenuDashboard.values());
		
		return selecionarOpcao(OpcaoMenuDashboard.values());
	}
	
	public OpcaoMenuInicial selecionarOpcaoMenuInicial() {
		imprimirTitulo("Introdução");
		System.out.println("Bem vindo ao Playlister. \nAqui você poderá cadastrar suas playlists de filmes.\n");

		exibirOpcoes(OpcaoMenuInicial.values());
		
		return selecionarOpcao(OpcaoMenuInicial.values());
	}
	
	public OpcaoMenuPaginaUsuario selecionarOpcaoMenuPaginaUsuario() {
		imprimirTitulo("Página do usuário");
		
		exibirOpcoes(OpcaoMenuPaginaUsuario.values());
		
		return selecionarOpcao(OpcaoMenuPaginaUsuario.values());
	}
	
	public Usuario cadastrar() {
		imprimirTitulo("Cadastro");
		
		System.out.println("Informe o seu e-mail: ");
		String email = preencherCampoTexto(255, false);
		
		System.out.println("Informe a sua senha: ");
		String senha = preencherCampoTexto(255, false);
		
		System.out.println("Informe a sua data de nascimento (dd/mm/aaaa): ");
		LocalDate dataNascimento = preencherData(false);
		
		Usuario usuario = new Usuario(email, senha, dataNascimento);
			
		return usuario;
	}
	
	public String[] entrar() {
		imprimirTitulo("Entrar");
		
		System.out.println("Informe o seu e-mail: ");
		String email = preencherCampoTexto(255, false);
		
		System.out.println("Informe a sua senha: ");
		String senha = preencherCampoTexto(255, false);
		
		String[] emailSenha = {email, senha};
		
		return emailSenha;
	}
	
	public void editarSenha(Usuario usuario) {
		imprimirTitulo("EDITAR SENHA");
		
		System.out.println("Informe a nova senha: ");
		String senha = preencherCampoTexto(255, false);
		
		usuario.setSenha(senha);
	}
	
	public void imprimirMensagemUsuarioNaoPossuiIdadeMinima() {
		imprimirTitulo("Erro");
		
		System.out.println("Desculpe, para acessar o aplicativo é necessário ter peo menos 16 anos.\n");
	}
	
	public void imprimirMensagemEmailJaCadastrado() {
		imprimirTitulo("Erro");
		
		System.out.println("Desculpe, este e-mail já está cadastrado.\n");
	}
	
	public void imprimirMensagemCadastroComSucesso() {
		imprimirTitulo("Sucesso");
		
		System.out.println("Cadastro realizado com sucesso!\n");
	}
	
	public void imprimirMensagemLoginInvalido() {
		imprimirTitulo("Erro");
		
		System.out.println("E-mail ou senha não são válidos.\n");
	}
	
	public void imprimirMensagemLoginComSucesso() {
		imprimirTitulo("Sucesso");
		
		System.out.println("Login realizado com sucesso!\n");
	}
	
	public void imprimirMensagemSenhaEditadaComSucesso() {
		imprimirTitulo("Sucesso");
		
		System.out.println("A sua senha foi editada com sucesso!.\n");
	}
	
	public void imprimirUsuarios(List<Usuario> usuarios) {
		imprimirTitulo("Lista de usuários");
		
		System.out.println("Confira o ID de todos usuários, seus e-mails e data de nascimento.\n");
		
		usuarios.stream().forEach((usuario) -> {
			System.out.println(
					usuario.getId() + " \t" + 
					usuario.getEmail() + " \t" + 
					usuario.getDataNascimento()
			);
		});
		
		System.out.println();
	}
	
}
