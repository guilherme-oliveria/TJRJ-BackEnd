package com.br.tjrj.model;

public class AutorFactory {
    public static Autor createAutor() {
        return Autor.builder()
                .id(1L)
                .nome("Autor Exemplo")
                .build();
    }

    public static Autor createAutorWithId(Long id) {
        return Autor.builder()
                .id(id)
                .nome("Autor Exemplo")
                .build();
    }

    public static Autor.AutorBuilder createAutorWithNome(String nome) {
        return Autor.builder()
                .id(1L)
                .nome(nome);
    }
}
