package br.com.alura.alurator;

import br.com.alura.alurator.conversor.ConversorXML;
import br.com.alura.alurator.ioc.ContainerIoC;
import br.com.alura.alurator.protocolo.MetodosDaInstancia;
import br.com.alura.alurator.protocolo.Reflexao;
import br.com.alura.alurator.protocolo.Request;

import java.util.HashMap;
import java.util.Map;

public class Alurator {
	
	private String pacoteBase;
	private ContainerIoC container = new ContainerIoC();
	private Map<Class<?>, Class<?>> mapaDeTipos = new HashMap<>();

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
		String nomeClasse = pacoteBase + nomeControle;
		Map<String, Object> parametros = request.getParametros();

		Class<?> classeControle = new Reflexao(nomeClasse).getClasse();
		Object instancia = container.getInstancia(classeControle);

		Object retorno = new MetodosDaInstancia(instancia)
								.getManipuladorMetodo(nomeMetodo, parametros)
								.comTratamentoDeExcecao((metodo, excecao) -> {
									System.out.println("Erro no método " + metodo.getName() + " da classe "
											+ metodo.getDeclaringClass().getName() + ".\n\n");
									return new RuntimeException("Erro no método!");
								})
								.executaMetodo();

		return new ConversorXML().converte(retorno);

	}

	public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {

		container.registra(tipoFonte, tipoDestino);

	}

}
