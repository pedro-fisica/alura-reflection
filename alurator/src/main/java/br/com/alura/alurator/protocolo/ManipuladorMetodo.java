package br.com.alura.alurator.protocolo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class ManipuladorMetodo {

    private Object instancia;
    private Method metodo;
    private Map<String, Object> parametros;
    private BiFunction<Method, InvocationTargetException, Object> tratadoraDeExcecao;

    public ManipuladorMetodo(Object instancia, Method metodo) {
        this.instancia = instancia;
        this.metodo = metodo;
    }

    public ManipuladorMetodo(Object instancia, Method metodo, Map<String, Object> parametros) {
        this.instancia = instancia;
        this.metodo = metodo;
        this.parametros = parametros;
    }

    public ManipuladorMetodo comTratamentoDeExcecao(BiFunction<Method, InvocationTargetException, Object> tratadoraDeExcecao) {
        this.tratadoraDeExcecao = tratadoraDeExcecao;
        return this;
    }

    public Object executaMetodo() {
        try {
            if (parametros == null) {
                return metodo.invoke(instancia);
            }

            List<Object> paramValues = new ArrayList<>();
            Arrays.stream(metodo.getParameters()).forEach(p ->
                    paramValues.add(parametros.get(p.getName())));

            return metodo.invoke(instancia, paramValues.toArray());

        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
