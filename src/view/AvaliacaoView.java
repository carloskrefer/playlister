package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import entity.Avaliacao;
import entity.Filme;
import entity.Playlist;
import entity.Usuario;
import view.PlaylistView.OpcaoMenuInicialPlaylist;

public class AvaliacaoView extends View {

	public enum OpcaoMenuAvaliacao {
		CADASTRAR, VISUALIZAR
	}
	
	
	public AvaliacaoView() {
		super();
	}
	
	public OpcaoMenuAvaliacao selecionarOpcaoMenuAvaliacao() {
		imprimirTitulo("Página das avaliações");

		exibirOpcoes(OpcaoMenuAvaliacao.values());
		
		return selecionarOpcao(OpcaoMenuAvaliacao.values());
	}
	
	public Avaliacao cadastrar(Filme filmePlaylist) {
		imprimirTitulo("Cadastro de avaliação");
		
		System.out.println("Informe a nota pra avaliação: ");
		int nota = preencherInteiroID();
		
		System.out.println("Preencha a data da avaliação:");
		LocalDate data = preencherData(false);
		
		System.out.println("Preencha uma observação: ");
		String obs = preencherCampoTexto(255, true);
			
		Avaliacao avaliacao = new Avaliacao(nota, data, obs, filmePlaylist);
		
		return avaliacao;
	}
	
}
