package service;

import java.time.LocalDate;
import java.time.Period;

import entity.Usuario;
import model.UsuarioModel;

public class UsuarioService {

	public UsuarioService() {
		
	}
	
	public void cadastrar(Usuario usuario) throws UsuarioNaoPossuiIdadeMinimaException,
		EmailJaCadastradoException {
		
		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
		
		if (idade < 16) {
			throw new UsuarioNaoPossuiIdadeMinimaException();
		}
		
		UsuarioModel usuarioModel = new UsuarioModel();
		
		boolean isEmailJaCadastrado = usuarioModel
				.buscar()
				.stream()
				.anyMatch(user -> user.getEmail().equals(usuario.getEmail()));
		
		if (isEmailJaCadastrado) {
			throw new EmailJaCadastradoException();
		}
		
		usuarioModel.cadastrar(usuario);
	}
	
	

}
