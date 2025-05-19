package com.br.tjrj.model;

public class AssuntoFactory {
    public static Assunto createAssunto() {
        return Assunto.builder()
                .id(1L)
                .descricao("Assunto Exemplo")
                .build();
    }

    public static Assunto createAssuntoWithId(Long id) {
        return Assunto.builder()
                .id(id)
                .descricao("Assunto Exemplo")
                .build();
    }

    public static Assunto.AssuntoBuilder createAssuntoWithDescricao(String descricao) {
        return Assunto.builder()
                .id(1L)
                .descricao(descricao);
    }
}
