package com.br.tjrj.model;

import java.math.BigDecimal;

public interface LivroPorAutorProjection {
    Long getAutorId();
    String getAutorNome();
    Long getLivroId();
    String getLivroTitulo();
    String getEditora();
    Integer getEdicao();
    String getAnoPublicacao();
    BigDecimal getValor();
    String getAssuntos();
}
