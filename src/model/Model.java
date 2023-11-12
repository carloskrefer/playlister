package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

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
	
	// Este método existe pra evitar repetição de código no cadastrar(), editar(), remover(), etc.
	//
	// Consumer<EntityManager> operacao
	// 		Consumer = Interface funcional do Java que possui um único argumento.
	//			Obs 1: 	Interface funcional é toda interface com um único método abstrato. Precisamos
	//		     		usar interfaces funcionais quando queremos, por exemplo, passar uma função 
	//			 		lambda como parâmetro.
	//			Obs 2:  O nome desse método abstrato, na interface Consumer, se chama accept(T t). Como
	//					o parâmetro genérico é <EntityManager>, ficou accept(EntityManager t).
	//					Esse método abstrato vai executar a função lambda passada.
	public void executarOperacaoBancoDados(Consumer<EntityManager> operacao) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistanceUnit);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		operacao.accept(em);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
	
	// TODO: colocar exceções
	public void cadastrar(T objeto) {
		
		executarOperacaoBancoDados((em) -> em.persist(objeto));
		
	}

	// TODO: colocar exceções
	public void editar(T objeto) {
		
		// O "T objeto" do parâmetro editar(T objeto) foi instanciado antes do "EntityManager em" do
		// executarOperacaoBancoDados() ser instanciado. Ou seja, esse
		// entity manager não está gerenciando esse objeto. Pra isso, estamos fazendo um "merge"
		// para que esse objeto passe a ser gerenciado por ele. Caso contrário,
		// a edição não será persistida.
		executarOperacaoBancoDados((em) -> em.merge(objeto));
		
	}
	
	// TODO: colocar exceções
	public List<T> buscar() {
		
		// No buscar() não adianta usar executarOperacoesBancoDados(), pois aqui queremos retorno, e lá
		// uso a interface Consumer que não tem retorno, pois o método persist() usado no cadastrar(), por
		// exemplo, não tem retorno.
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
	
	// TODO: colocar exceções
	public void remover(T objeto) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistanceUnit);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();

		// Explicação do que faz o merge() se encontra no método editar()
		T objetoGerenciadoPelaEntityAtual = em.merge(objeto);
		
		// A única diferença do editar() é que pra remover() é que temos que informar o objeto gerenciado.
		// Se abaixo informar remove(objeto), dará erro, pois 'objeto' não está gerenciado. É o merge()
		// que retorna o objeto gerenciado.
		em.remove(objetoGerenciadoPelaEntityAtual);
				
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
	
	public static void main(String[] args) {
		// teste mostrando como que faz a busca e update
		Model<Usuario> model = new Model(Usuario.class);
		
		model.cadastrar(new Usuario("carlos.krefer@pucpr.edu.br", "123", LocalDate.now()));
//		List<Usuario> usuarios = model.buscar();
//		
//		Usuario usuario = usuarios
//				.stream()
//				.filter(user -> user.getEmail().equals("bruno@hotmail.com"))
//				.findFirst()
//				.get();
//		
//		model.remover(usuario);
		
		
		
		
	}
	
	

}
