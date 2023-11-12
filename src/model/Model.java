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
		
		// O "T objeto" foi instanciado antes do "EntityManager em" ser instanciado. Ou seja,
		// o entity manager não está gerenciando esse objeto. Pra isso, estamos fazendo um "merge"
		// para que esse objeto passe a ser gerenciado pelo entity manager atual. Caso contrário,
		// a edição não será persistida.
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
		// teste mostrando como que faz a busca e update
		Model<Usuario> model = new Model(Usuario.class);
		List<Usuario> usuarios = model.buscar();
		
		Usuario usuario = usuarios
				.stream()
				.filter(user -> user.getEmail().equals("rogerio@hotmail.com"))
				.findFirst()
				.get();
		
		usuario.setSenha("322");
		
		model.editar(usuario);
		
		
	}
	
	

}
