package view;

import java.time.LocalDate;
import java.util.List;

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
	
	public void imprimirMensagemCadastroComSucesso() {
		imprimirTitulo("Sucesso");
		
		System.out.println("Cadastro realizado com sucesso!\n");
	}
	
	public void imprimirMensagemEdicaoComSucesso() {
		imprimirTitulo("Sucesso");
		
		System.out.println("Edição de filme realizada com sucesso!\n");
	}
	
	public void imprimirMensagemNaoHaFilme() {
		imprimirTitulo("Erro");
		
		System.out.println("Não há filmes cadastrados para a playlist selecionada.\n");
	}
	
	public void editar(Filme filme) {
		imprimirTitulo("EDITAR FILME");
		
		System.out.println("Informe o novo nome do filme: ");
		String nome = preencherCampoTexto(255, false);
		
		System.out.println("Informe a nova data de estréia do filme (dd/mm/aaaa): ");
		LocalDate dataEstreia = preencherData(false);
		
		System.out.println("Informe a nova duracao do filme em minutos, sem casas decimais: ");
		int duracao = preencherInteiroID();
		
		filme.setNome(nome);
		filme.setDataEstreia(dataEstreia);
		filme.setDuracaoMinutos(duracao);
	}

	public void imprimirFilmes(List<Filme> filmes) {
		imprimirTitulo("Lista de filmes da playlist selecionada");
		
		if (filmes.isEmpty()) {
			System.out.println("Não há nenhum para a playlist selecionada.\n");
			return;
		}
		
		System.out.println("Confira o ID de todos seus filmes, suas datas de estréia e de criação.\n");
		
		filmes.stream().forEach((filme) -> {
			System.out.println(
					filme.getId() + " \t" + 
					filme.getNome() + " \t" + 
					filme.getDataEstreia()
			);
		});
		
		System.out.println();
	}
	
	public Filme selecionarFilme(List<Filme> filmesUsuarioPlaylist) {
		imprimirTitulo("Seleção de ID do filme");
		
		System.out.println("Informe o ID do filme: ");
		int idFilme = preencherInteiroID();
		
		return filmesUsuarioPlaylist.stream().filter((filme) -> 
			filme.getId() == idFilme
				).findFirst().get();	
		
	}
	
	public void imprimirMensagemNaoHaIdFilmeSelecionado() {
		imprimirTitulo("Erro");
		
		System.out.println("Não há filme para o ID selecionado.\n");
	}
	
}
