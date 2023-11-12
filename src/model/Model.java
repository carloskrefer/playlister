package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import entity.Avaliacao;
import entity.Filme;
import entity.Playlist;
import entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Model<T> {
	private final String persistanceUnit = "playlisterPU";
	Class<T> classe;
	
	public Model(Class<T> classe) {
		this.classe = classe;
	}
	
	// TODO: colocar exceções
	public void cadastrar(T objeto) {
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistanceUnit);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(objeto);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

	// TODO: colocar exceções
	public void editar(T objeto) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistanceUnit);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(objeto);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
	
	// TODO: colocar exceções
	public List<T> buscar() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistanceUnit);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT obj FROM " + classe.getSimpleName() + " obj");
		List<T> objetos = query.getResultList();
				
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return objetos;
		
	}
	
	public static void main(String[] args) {
		List<Usuario> usuarios = new Model(Usuario.class).buscar();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getEmail());
		}
	}
	
	

}
