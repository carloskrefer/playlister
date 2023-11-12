package test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import entity.Playlist;
import entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PlaylistTest {

	public static void main(String[] args) {

		Usuario usuario = new Usuario("usuario@hotmail.com", "123", LocalDate.now());
		
		Playlist playlist = new Playlist("Filmes Anos 80", LocalDateTime.now(), usuario);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("playlisterPU");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.persist(playlist);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		

	}

}
