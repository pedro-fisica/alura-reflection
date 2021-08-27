package br.com.alura.alurator.protocolo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ManipuladorMetodo {

    private Object instancia;
    private String nomeMetodo;

    public ManipuladorMetodo(Object instancia, String nomeMetodo) {
        this.instancia = instancia;
        this.nomeMetodo = nomeMetodo;
    }

    public Object invoca() {
        try {
            Method method = instancia.getClass().getMethod("lista");
            return method.invoke(instancia);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("O método não pôde ser invocado: " + e.getTargetException());
        }

    }
}
