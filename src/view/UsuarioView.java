package view;

import java.time.LocalDate;

import entity.Usuario;

public class UsuarioView extends View {
	
	public enum OpcaoMenuInicial {
		ENTRAR, CADASTRAR
	}
	
	public UsuarioView() {
		super();
	}
	
	public OpcaoMenuInicial selecionarOpcaoMenuInicial() {
		imprimirTitulo("Introdução");
		System.out.println("Bem vindo ao Playlister. \nAqui você poderá cadastrar suas playlists de filmes.\n");

		exibirOpcoes(OpcaoMenuInicial.values());
		
		return selecionarOpcao(OpcaoMenuInicial.values());
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
	
	public void imprimirMensagemUsuarioNaoPossuiIdadeMinima() {
		imprimirTitulo("Erro");
		
		System.out.println("Desculpe, para acessar o aplicativo é necessário ter peo menos 16 anos.\n");
	}
	
	public void imprimirMensagemEmailJaCadastrado() {
		imprimirTitulo("Erro");
		
		System.out.println("Desculpe, este e-mail já está cadastrado.\n");
	}
	
	
}
