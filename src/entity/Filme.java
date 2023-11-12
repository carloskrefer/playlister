package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Filme {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=255)
	private String nome;
	
	@ManyToOne
	//O Join Column vai dizer qual coluna estar� recebendo esta chave
	@JoinColumn(name="playlist_id")
	private Playlist playlist;
	
	private LocalDate dataEstreia;
	
	private int duracaoMinutos;
	
	public Filme() {
		
	}

	public Filme(String nome, Playlist playlist, LocalDate dataEstreia, int duracaoMinutos) {
		this.nome = nome;
		this.playlist = playlist;
		this.dataEstreia = dataEstreia;
		this.duracaoMinutos = duracaoMinutos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataEstreia() {
		return dataEstreia;
	}

	public void setDataEstreia(LocalDate dataEstreia) {
		this.dataEstreia = dataEstreia;
	}

	public int getDuracaoMinutos() {
		return duracaoMinutos;
	}

	public void setDuracaoMinutos(int duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}

	public Long getId() {
		return id;
	}

	public Playlist getPlaylist() {
		return playlist;
	}
	
	

}
