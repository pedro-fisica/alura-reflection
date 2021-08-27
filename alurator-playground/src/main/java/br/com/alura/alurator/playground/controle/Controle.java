package br.com.alura.alurator.playground.controle;

import java.util.ArrayList;
import java.util.List;

public class Controle {
	
	private List<String> lista = new ArrayList<>();
	
	public Controle() {}
	
	public Controle(String s) {}
	
	private Controle(String s, String t) {}
	
	
	public List<String> getLista() {
		return lista;
	}
}
