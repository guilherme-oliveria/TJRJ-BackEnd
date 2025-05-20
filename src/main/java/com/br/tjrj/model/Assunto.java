package com.br.tjrj.model;

import jakarta.persistence.*;
import lombok.*;

import java.lang.annotation.Annotation;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assunto")
@SequenceGenerator(name = Assunto.SEQUENCE_NAME,
        sequenceName = Assunto.SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
public class Assunto implements Entity {

    public static final String SEQUENCE_NAME = "seq_assunto";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Column(name = "cod_as")
    private Long id;

    @Column(name = "descricao", length = 20)
    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    private Set<Livro> livros;


    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}