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
@Table(name = "autor")
@SequenceGenerator(name = Autor.SEQUENCE_NAME,
        sequenceName = Autor.SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
public class Autor implements  Entity {

    public static final String SEQUENCE_NAME = "seq_autor";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Column(name = "cod_au")
    private Long id;

    @Column(name = "nome", length = 40)
    private String nome;

    @ManyToMany(mappedBy = "autores")
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
