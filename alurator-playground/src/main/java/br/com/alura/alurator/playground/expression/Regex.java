package br.com.alura.alurator.playground.expression;

import java.util.Arrays;

public class Regex {

    public static void main(String[] args) {
        String expressao = "/controle/metodo?name1=value1&name2=value2";
        final String[] split = expressao.replaceFirst("/", "").split("");
        for (String s : split) {
            System.out.println(s);
        }
//        final String params = split[2];


    }
}
