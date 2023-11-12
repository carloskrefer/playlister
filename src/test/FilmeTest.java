package test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import entity.Filme;
import entity.Playlist;
import entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FilmeTest {

	public static void main(String[] args) {
		
		Usuario usuario = new Usuario("rogerio@hotmail.com", "123", LocalDate.now());
		
		Playlist playlist = new Playlist("Filmes Anos 90", LocalDateTime.now(), usuario);
		
		Filme filme = new Filme("MIB", playlist, LocalDate.now(), 30);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("playlisterPU");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.persist(playlist);
		em.persist(filme);
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
