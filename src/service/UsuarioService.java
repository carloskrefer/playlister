package service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import entity.Usuario;
import model.UsuarioModel;

public class UsuarioService {
	UsuarioModel usuarioModel;
	
	public UsuarioService() {
		usuarioModel = new UsuarioModel();
	}
	
	public void cadastrar(Usuario usuario) throws UsuarioNaoPossuiIdadeMinimaException,
		EmailJaCadastradoException {
		
		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
		
		if (idade < 16) {
			throw new UsuarioNaoPossuiIdadeMinimaException();
		}
		
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
		
		usuarioModel = new UsuarioModel();
		
		try {
			usuario = usuarioModel.buscarUsuarioPorLoginMaisSenha(email, senha);
		} catch (Exception e) {
			throw new LoginInvalidoException();
		}
		
		return usuario;	
	}
	
	public List<Usuario> buscarTodosUsuarios() {
		return usuarioModel.buscar();
	}
	
	public void deletarConta(Usuario usuario) {	
		usuarioModel.remover(usuario);
	}
	
	public void editarSenha(Usuario usuario) {
		usuarioModel.editar(usuario);
	}
	
	

}
