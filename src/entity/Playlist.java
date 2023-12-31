package entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Playlist {

	public Long getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=255, unique=true)
	private String nome;
	
	@ManyToOne
	//O Join Column vai dizer qual coluna estar� recebendo esta chave
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	// CascadeType.REMOVE faz com que remova todas playlists
	// deste usuário quando ele é removido.
	// mappedBy é o nome do atributo com tag @ManyToOne na playlist.
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "playlist")
    private List<Filme> filmes;
	
	private LocalDateTime dataHoraCriacao;
	
	public Playlist() {
		
	}
	
	public Playlist(String nome, LocalDateTime dataHoraCriacao, Usuario usuario) {
		this.nome = nome;
		this.dataHoraCriacao = dataHoraCriacao;
		this.usuario = usuario;
	}
	

}
