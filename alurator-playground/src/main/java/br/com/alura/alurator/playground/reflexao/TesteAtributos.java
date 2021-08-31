package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.modelo.Produto;
import br.com.alura.alurator.playground.modelo.SuperProduto;

import java.lang.reflect.Field;

public class TesteAtributos {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        SuperProduto produto = new Produto("Geladeira", 3000, "Brastemp");

        Field[] declaredFields = produto.getClass().getDeclaredFields();

        String pacoteClasse = produto.getClass().getName();
        String nome = pacoteClasse.substring(pacoteClasse.lastIndexOf(".") + 1).toLowerCase();

        String xml = "<" + nome + ">\n";
        for (Field field : declaredFields) {
            field.setAccessible(true);
            xml = xml + "\t<" + field.getName() + ">" + field.get(produto) + "</" + field.getName() + ">\n";
        }
        xml = xml + "</" + nome + ">";
        System.out.println(xml);
    }
}
