package test;

import java.time.LocalDate;

import entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UsuarioTest {

	public static void main(String[] args) {
		
		Usuario usuario = new Usuario("Joao das Couves", "senha123", LocalDate.now());
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("playlisterPU");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		 
	}
	
}
