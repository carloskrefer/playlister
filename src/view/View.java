package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class View {
	Scanner scanner;
	
	public View() {
		scanner = new Scanner(System.in);
	}
	
	protected <T extends Enum<T>> void exibirOpcoes(T[] opcoes) {
		
		System.out.println("Selecione uma opção: ");
		
		for (int i = 0; i < opcoes.length; i++) {
			System.out.println("\t" + i + ". " + opcoes[i]);
		}
		
	}

	protected <T extends Enum<T>> T selecionarOpcao(T[] opcoesValidas) {
		
		String opcaoSelecionada;
		boolean isEntradaValida;
		
		do {
			opcaoSelecionada = scanner.nextLine();
			isEntradaValida = validarOpcaoSelecionada(opcoesValidas, opcaoSelecionada);
			
			System.out.println();
			if (!isEntradaValida) {
				System.out.println("Opção selecionada não existe! Tente novamente.\n");
			}
		} while (!isEntradaValida);
		
		return opcoesValidas[Integer.parseInt(opcaoSelecionada)];
		
	}
	
	private <T extends Enum<T>> boolean validarOpcaoSelecionada(T[] opcoesValidas, String opcaoSelecionada) {
		
		try {
			Integer numeroSelecionado = Integer.parseInt(opcaoSelecionada);
			if ((numeroSelecionado >= 0) && (numeroSelecionado < opcoesValidas.length)) {
				return true;
			}
		} catch (Exception e) { }
		
		return false;
		
	}
	
	protected String preencherCampoTexto(int limiteCaracteres, boolean isVazioPermitido) {
		
		String textoEscrito;
		boolean isEntradaValida = true;
		
		while (true) {
			textoEscrito = scanner.nextLine();
			
			isEntradaValida = (textoEscrito.length() > 255) ? false : true;
			
			if (textoEscrito.length() > 255) {
				System.out.println("Este campo não pode ter mais de 255 caracteres.\n");
				continue;
			}
			
			if (!isVazioPermitido) {
				isEntradaValida = (textoEscrito.length() == 0) ? false : isEntradaValida;
				if (textoEscrito.length() == 0) {
					System.out.println("Este campo não pode ser vazio.\n");
					continue;
				}
			}
			
			break;
		}
		
		System.out.println();
		return textoEscrito;
		
	}
	
	protected LocalDate preencherData(boolean isVazioPermitido) {
		
		LocalDate localDate;
		
		while(true) {
			try { 
				String textoData = preencherCampoTexto(0, isVazioPermitido);
				
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
				localDate = LocalDate.parse(textoData, formato);
			
				break;	
			} catch(Exception e) { 
				System.out.println("Formato inválido.\n");
			}
		}
		
		System.out.println();
		return localDate;
		
	}
	
	protected int preencherInteiroID() {
		
		int numero;
		
		while(true) {
			try { 
				String texto = scanner.nextLine();
				
				numero = Integer.parseInt(texto);
			
				if (numero < 0) {
					throw new Exception();
				}
				
				break;	
			} catch(Exception e) { 
				System.out.println("Formato inválido.\n");
			}
		}
		
		System.out.println();
		
		return numero;
		
	}
	
	protected void imprimirTitulo(String titulo) {
		System.out.println("------- " + titulo.toUpperCase() + " -------\n");
	}
	
	
}
