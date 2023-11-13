package model;

import java.util.List;

import entity.Avaliacao;
import entity.Filme;

public class AvaliacaoModel extends ModelCreateDeleteEditBusca<Avaliacao> {

	public AvaliacaoModel() {
		super(Avaliacao.class);
	}
	
	public List<Avaliacao> buscarTodasAvaliacoesFilme(Filme filme) {
		return buscar()
				.stream()
				.filter(avaliacao -> avaliacao.getId() == filme.getId())
				.toList();
	}

}
