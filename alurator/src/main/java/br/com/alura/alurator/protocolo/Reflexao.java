package br.com.alura.alurator.protocolo;

public class Reflexao {

    private String nomeClasse;

    public Reflexao(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public Class<?> getClasse() {
        try {
            return Class.forName(nomeClasse);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ManipuladorClasse getManipuladorClasse() {
        return new ManipuladorClasse(getClasse());
    }

}
