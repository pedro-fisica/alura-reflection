package br.com.alura.alurator.playground.controle;

import java.util.ArrayList;
import java.util.List;

public class Controle {
	
	private List<String> lista = new ArrayList<>();
	
	public Controle() {
		this.lista.add("item 1");
		this.lista.add("item 2");
		this.lista.add("item 3");
	}
	
	public Controle(String s) {
		this.lista.add("item 1");
		this.lista.add("item 2");
		this.lista.add("item 3");
	}
	
	private Controle(String s, String t) {
		this.lista.add("item 1");
		this.lista.add("item 2");
		this.lista.add("item 3");
	}
	
	
	public List<String> getLista() {
		return lista;
	}
}
