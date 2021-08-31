package br.com.alura.alurator.protocolo;

import java.util.HashMap;
import java.util.Map;

public class Request {

	/*
	Casos possíveis:
	/controle/metodo
	/controle/metodo?name=value
	/controle/metodo?name1=value1&name2=value2
	 */

	private String nomeControle;
	private String nomeMetodo;
	private Map<String, Object> parametros;
	
	public Request(String url) {
		String[] partesUrl = url.split("\\?");

		String[] endereco = partesUrl[0].replaceFirst("/", "").split("/");
		this.nomeControle = Character.toUpperCase(endereco[0].charAt(0)) +
				endereco[0].substring(1) + "Controller";
		this.nomeMetodo = endereco[1];

		if (partesUrl.length == 2) {
			String params = partesUrl[1];
			this.parametros = params.length() > 1 ?
							  new QueryParamsBuilder().comParametros(params).build() :
							  new HashMap<>();
		}
	}

	public String getNomeControle() {
		return this.nomeControle;
	}

	public String getNomeMetodo() {
		return this.nomeMetodo;
	}

	public Map<String, Object> getParametros() {
		return this.parametros;
	}
}
