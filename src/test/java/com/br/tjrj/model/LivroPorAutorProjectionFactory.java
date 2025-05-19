package com.br.tjrj.model;

import java.math.BigDecimal;

public class LivroPorAutorProjectionFactory {
    public static LivroPorAutorProjection createLivroPorAutorProjection() {
        return new LivroPorAutorProjection() {
            public Long getAutorId() { return 1L; }
            public String getAutorNome() { return "Autor Exemplo"; }
            public Long getLivroId() { return 10L; }
            public String getLivroTitulo() { return "Título do Livro"; }
            public String getEditora() { return "Editora Exemplo"; }
            public Integer getEdicao() { return 1; }
            public String getAnoPublicacao() { return "2024"; }
            public BigDecimal getValor() { return BigDecimal.TEN; }
            public String getAssuntos() { return "Assunto1, Assunto2"; }
        };
    }
}
