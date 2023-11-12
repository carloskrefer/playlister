package entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	public Usuario(String email, String senha, LocalDate dataNascimento) {
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	public Usuario() {
		
	}
	
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Column(length=255, unique=true)
	private String email;
	
	@Column(length=255)
	private String senha;
	
	private LocalDate dataNascimento;
	
	// CascadeType.REMOVE faz com que remova todas playlists
	// deste usuário quando ele é removido.
	// mappedBy é o nome do atributo com tag @ManyToOne na playlist.
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "usuario")
    private List<Playlist> playlists;

}
