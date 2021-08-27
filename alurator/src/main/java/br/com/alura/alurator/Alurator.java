package br.com.alura.alurator;

import br.com.alura.alurator.protocolo.Reflexao;
import br.com.alura.alurator.protocolo.Request;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Alurator {
	
	private String pacoteBase;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}
	
	public Object executa(String url) {
		// TODO - processa a requisicao executando o metodo
		// da classe em questao
		
		// Produto lista
		
		// produto -> roduto

		Request request = new Request(url);
		String nomeControle = request.getNomeControle();
		String nomeMetodo = request.getNomeMetodo();

		Object retorno = new Reflexao(pacoteBase + nomeControle)
									.getManipuladorClasse()
									.getManipuladorMetodo(nomeMetodo)
									.invoca();

		return retorno;

	}
}
