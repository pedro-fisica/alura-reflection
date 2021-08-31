package br.com.alura.alurator.conversor;

import br.com.alura.alurator.anotacao.NovaTagXml;

import java.lang.reflect.Field;
import java.util.Collection;

public class ConversorXML {

    public String converte(Object objeto) {

        try {
            StringBuilder xmlBuilder = new StringBuilder();
            Class<?> classe = objeto.getClass();

            if (objeto instanceof Collection) {
                String xml = "<lista>";
                for (Object o : (Collection<Object>) objeto) {
                    xml = xml + this.converte(o);
                }
                return xml + "</lista>";
            }

            String nomeClasse = classe.getDeclaredAnnotation(NovaTagXml.class) != null
                                ? classe.getDeclaredAnnotation(NovaTagXml.class).value()
                                : classe.getName();

            xmlBuilder.append("<" + nomeClasse + ">");
            for (Field atributo : classe.getDeclaredFields()) {
                atributo.setAccessible(true);
                String nomeAtributo = atributo.getDeclaredAnnotation(NovaTagXml.class) != null
                                      ? atributo.getDeclaredAnnotation(NovaTagXml.class).value()
                                      : atributo.getName();

                xmlBuilder.append("<" + nomeAtributo + ">"
                        + atributo.get(objeto)
                        + "</" + nomeAtributo + ">");
            }
            xmlBuilder.append("</" + nomeClasse + ">");
            return xmlBuilder.toString();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
