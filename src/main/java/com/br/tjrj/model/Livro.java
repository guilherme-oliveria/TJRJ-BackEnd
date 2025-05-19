package com.br.tjrj.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "livro")
@SequenceGenerator(name = Livro.SEQUENCE_NAME,
        sequenceName = Livro.SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
public class Livro implements Entity {

    public static final String SEQUENCE_NAME = "seq_livro";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Column(name = "cod_livro")
    private Long id;

    @Column(name = "titulo", length = 40, nullable = false)
    private String titulo;

    @Column(name = "editora", length = 40)
    private String editora;

    @Column(name = "edicao")
    private Integer edicao;

    @Column(name = "ano_publicacao", length = 4)
    private String anoPublicacao;

    @Column(name = "valor", precision = 16, scale = 4)
    private BigDecimal valor;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_codl", referencedColumnName = "cod_livro"),
            inverseJoinColumns = @JoinColumn(name = "autor_codau", referencedColumnName = "cod_au")
    )
    private List<Autor> autores;

    @ManyToMany
    @JoinTable(
            name = "livro_assunto",
            joinColumns = @JoinColumn(name = "livro_codl", referencedColumnName = "cod_livro"),
            inverseJoinColumns = @JoinColumn(name = "assunto_codas", referencedColumnName = "cod_as")
    )
    private List<Assunto> assuntos;

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return this.getClass();
    }
}
