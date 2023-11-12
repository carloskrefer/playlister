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
				.conferirSeEmailJaEstaCadastrado(usuario.getEmail());
		
		if (isEmailJaCadastrado) {
			throw new EmailJaCadastradoException();
		}
		
		usuarioModel.cadastrar(usuario);
	}
	
	public Usuario entrar(String[] dadosLogin) throws LoginInvalidoException {
		Usuario usuario;
		String email = dadosLogin[0];
		String senha = dadosLogin[1];
		
		UsuarioModel usuarioModel = new UsuarioModel();
		
		try {
			usuario = usuarioModel.buscarUsuarioPorLoginMaisSenha(email, senha);
		} catch (Exception e) {
			throw new LoginInvalidoException();
		}
		
		return usuario;
		
	}
	
	

}
