package view;

import java.time.LocalDate;

import entity.Filme;
import entity.Playlist;
import entity.Usuario;
import view.UsuarioView.OpcaoMenuDashboard;

public class FilmeView extends View {
	
	public FilmeView() {
		super();
	}
	
	public Filme cadastrar(Playlist playlistDoFilme) {
		imprimirTitulo("Cadastro");
		
		System.out.println("Informe o nome do filme: ");
		String nome = preencherCampoTexto(255, false);
		
		System.out.println("Informe a data de estréia do filme (dd/mm/aaaa): ");
		LocalDate dataEstreia = preencherData(false);
		
		System.out.println("Informe a duracao do filme em minutos, sem casas decimais: ");
		int duracao = preencherInteiroID();
		
		Filme filme = new Filme(nome, playlistDoFilme, dataEstreia, duracao);
			
		return filme;
	}

	public void imprimirMensagemFilmeJaCadastrado() {
		imprimirTitulo("Erro");
		
		System.out.println("Desculpe, este filme já está cadastrado nesta playlist.\n");
	}

}
