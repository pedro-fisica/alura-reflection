package br.com.alura.alurator.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;

public class ContainerIoC {

    private Map<Class<?>, Class<?>> mapaDeTipos = new HashMap<>();

    public Object getInstancia(Class<?> tipoFonte) {

        try {
            Class<?> tipoDestino = mapaDeTipos.get(tipoFonte);
            if (tipoDestino != null) {
                return getInstancia(tipoDestino);
            }
            Optional<Constructor<?>> construtorPadrao = Arrays.stream(tipoFonte.getDeclaredConstructors())
                                        .filter(constructor -> constructor.getParameterCount() == 0)
                                        .findFirst();

            if (construtorPadrao.isPresent())
                return construtorPadrao.get().newInstance();

            Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0];
            List<Object> parametros = new ArrayList<>();
            for (Parameter p : construtor.getParameters()) {
                parametros.add(getInstancia(p.getType()));
            }
            return construtor.newInstance(parametros.toArray());

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {

        mapaDeTipos.put(tipoFonte, tipoDestino);
    }

}
