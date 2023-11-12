package model;

import entity.Usuario;

public class UsuarioModel extends ModelCreateDeleteEditBusca<Usuario> {

	public UsuarioModel() {
		super(Usuario.class);
	}
	
	public boolean conferirSeEmailJaEstaCadastrado(String email) {
		return buscar()
				.stream()
				.anyMatch(user -> user.getEmail().equals(email));
	}
	
	public Usuario buscarUsuarioPorLoginMaisSenha(String email, String senha) {
		return buscar()
				.stream()
				.filter(user -> {
					return (user.getEmail().equals(email) &&
							user.getSenha().equals(senha));
				})
				.findFirst()
				.get();
	}
	
	

}
