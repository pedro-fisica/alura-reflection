package br.com.alura.alurator.protocolo;

public class ManipuladorClasse {

    private Class<?> controleClasse;

    public ManipuladorClasse(Class<?> classeControle) {
        this.controleClasse = classeControle;
    }

    public MetodosDaInstancia getNovaInstancia() {
        try {
            return new MetodosDaInstancia(controleClasse.newInstance());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

}
