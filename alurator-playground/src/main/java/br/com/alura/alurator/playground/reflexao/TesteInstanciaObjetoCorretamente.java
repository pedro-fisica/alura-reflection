package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import br.com.alura.alurator.playground.controle.SubControle;

public class TesteInstanciaObjetoCorretamente {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException {
		
		Class<SubControle> subControleClasse1 = SubControle.class;
		
		Class<?> subControleClasse2 =
				Class.forName("br.com.alura.alurator.playground.controle.SubControle");
		
		Class<?> controleClasse1 =
				Class.forName("br.com.alura.alurator.playground.controle.Controle");
		
		try {
			controleClasse1.getDeclaredConstructor().newInstance();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			System.out.println(e.getTargetException());
		}
		
//		controleClasse1.newInstance();
		
//		Constructor<SubControle> construtorSubControle = 
//				subControleClasse1.getDeclaredConstructor();
//		
//		System.out.println(construtorSubControle);
//		
//		construtorSubControle.setAccessible(true);
//		Object subControle = construtorSubControle.newInstance();
//		
//		System.out.println(subControle);

		for (Method m : subControleClasse1.getMethods()) {
			System.out.println(m);
		}
		System.out.println("");
		for (Method m : subControleClasse1.getDeclaredMethods()) {
			System.out.println(m);
		}

		Method metodoSubControle2 = subControleClasse1.getDeclaredMethod("metodoSubControle1");
		metodoSubControle2.setAccessible(true);
		try {
			Object object = metodoSubControle2.invoke(new SubControle());
			System.out.println(object);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro na execução do método: " + e.getTargetException());
		}
	}

}
