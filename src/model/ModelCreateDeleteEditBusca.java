package model;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class ModelCreateDeleteEditBusca<T> extends ModelCreateDeleteEdit<T> {
	private Class<T> classe;
	
	public ModelCreateDeleteEditBusca(Class<T> classe) {
		this.classe = classe;
	}

	// A interface 'Function' funciona de maneira parecida que o 'Consumer' da superclasse,
	// com a exceção que Function tem retorno e seu método abstrato  se chama apply. 
	// O resultado retornado é do tipo genérico List<T> informado no tipo genérico
	// <EntityManager, List<T>>.
	public List<T> executarOperacaoBancoDados(Function<EntityManager, List<T>> operacao) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistanceUnit);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<T> listaResultados = operacao.apply(em);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return listaResultados;
		
	}
	
	// TODO: colocar exceções
	public List<T> buscar() {
				
		List<T> listaResultados = executarOperacaoBancoDados((em) -> {
			Query query = em.createQuery("SELECT obj FROM " + classe.getSimpleName() + " obj");
			return query.getResultList();
		});
		
		return listaResultados;
		
	}
	
	// TODO: colocar exceções
	public List<T> buscar(String select) {
		
		List<T> listaResultados = executarOperacaoBancoDados((em) -> {
			Query query = em.createQuery(select);
			return query.getResultList();
		});
		
		return listaResultados;
		
	}

}
