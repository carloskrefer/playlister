package service;

import java.util.List;

import entity.Avaliacao;
import entity.Filme;
import entity.Playlist;
import model.AvaliacaoModel;

public class AvaliacaoService {
	AvaliacaoModel avaliacaoModel;
	
	public AvaliacaoService() {
		avaliacaoModel = new AvaliacaoModel();
		
	}
	
	public void cadastrar(Avaliacao avaliacao) {
		
		avaliacaoModel.cadastrar(avaliacao);

	}
	
	public List<Avaliacao> buscar(Filme filme) {
		return avaliacaoModel.buscar().stream().filter((av) -> av.getId() == filme.getId()).toList();
	}

}
