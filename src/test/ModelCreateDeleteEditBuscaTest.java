package test;

import java.time.LocalDate;
import java.util.List;

import entity.Usuario;
import model.ModelCreateDeleteEditBusca;

public class ModelCreateDeleteEditBuscaTest {

	public static void main(String[] args) {
		
		// Teste CREATE
		Usuario usuario = new Usuario("usuario22@hotmail.com", "123", LocalDate.now());
		
		ModelCreateDeleteEditBusca<Usuario> model = 
				new ModelCreateDeleteEditBusca<Usuario>((Class<Usuario>) usuario.getClass());
		
		model.cadastrar(usuario);
		
		
		// Teste UPDATE e BUSCA
//		List<Usuario> usuarios = model.buscar();
//		
//		Usuario usuarioBuscado = usuarios
//				.stream()
//				.filter(user -> user.getEmail().equals("jorge@hotmail.com"))
//				.findFirst()
//				.get();
//		
//		usuarioBuscado.setSenha("322");
//		
//		model.editar(usuarioBuscado);
		
		
		// Teste DELETE
//		model.remover(usuarioBuscado);


	}

}
