package br.com.alura.alurator.protocolo;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class MetodosDaInstancia {

    private Object instancia;

    public MetodosDaInstancia(Object instancia) {
        this.instancia = instancia;
    }

    public ManipuladorMetodo getManipuladorMetodo(String nomeMetodo) {
        try {
            return new ManipuladorMetodo(instancia, instancia.getClass().getMethod(nomeMetodo));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public ManipuladorMetodo getManipuladorMetodo(String nomeMetodo, Map<String, Object> parametros) {
        if (parametros == null) {
            return getManipuladorMetodo(nomeMetodo);
        }
        final Method metodo = Arrays.stream(instancia.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals(nomeMetodo)
                        && method.getParameterCount() == parametros.values().size()
                        && Arrays.stream(method.getParameters())
                        .allMatch(arg -> parametros.containsKey(arg.getName())
                                && parametros.get(arg.getName()).getClass().equals(arg.getType()))

                )
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Método não encontrado"));

        return new ManipuladorMetodo(instancia, metodo, parametros);
    }

}
