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

		Usuario usuario = new Usuario("zico@hotmail.com", "123", LocalDate.now());
		
		Playlist playlist = new Playlist("Filmes Ação", LocalDateTime.now(), usuario);
		
		Filme filme = new Filme("MIB 2", playlist, LocalDate.now(), 50);
		
		Avaliacao avaliacao = new Avaliacao(2, LocalDate.now(), "Filme muito ruim!", filme);
		
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
