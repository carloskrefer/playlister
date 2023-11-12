package test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import entity.Avaliacao;
import entity.Filme;
import entity.Playlist;
import entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AvaliacaoTest {

	public static void main(String[] args) {

		Usuario usuario = new Usuario("zacarias@hotmail.com", "123", LocalDate.now());
		
		Playlist playlist = new Playlist("Filmes Doidões", LocalDateTime.now(), usuario);
		
		Filme filme = new Filme("MIB 2", playlist, LocalDate.now(), 120);
		
		Avaliacao avaliacao = new Avaliacao(3, LocalDate.now(), "Filme ruim.", filme);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("playlisterPU");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.persist(playlist);
		em.persist(filme);
		em.persist(avaliacao);
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
