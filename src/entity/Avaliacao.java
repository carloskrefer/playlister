package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Avaliacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int nota;
	
	private LocalDate dataAvaliacao;
	
	@Column(length=255)
	private String observacao;
	
	@OneToOne
	@JoinColumn(name="filme_id")
	private Filme filme;
	
	public Avaliacao(int nota, LocalDate dataAvaliacao, String observacao, Filme filme) {
		this.nota = nota;
		this.dataAvaliacao = dataAvaliacao;
		this.observacao = observacao;
		this.filme = filme;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public LocalDate getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(LocalDate dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Long getId() {
		return id;
	}

	public Filme getFilme() {
		return filme;
	}

	public Avaliacao() {
		// TODO Auto-generated constructor stub
	}

	
	

}
