package br.com.alura.alurator.protocolo;

import java.lang.reflect.InvocationTargetException;

public class ManipuladorClasse {

    private Class<?> controleClasse;

    public ManipuladorClasse(Class<?> classeControle) {
        this.controleClasse = classeControle;
    }

    public ManipuladorMetodo getManipuladorMetodo(String nomeMetodo) {
        try {
            return new ManipuladorMetodo(controleClasse.newInstance(), nomeMetodo);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

}
