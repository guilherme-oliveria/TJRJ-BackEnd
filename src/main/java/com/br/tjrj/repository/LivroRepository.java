package com.br.tjrj.repository;

import com.br.tjrj.model.Livro;
import com.br.tjrj.model.LivroPorAutorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório para Livro.
 *
 * @author guilherme-oliveria
 */
@Repository
public interface LivroRepository  extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);

    @Query(value = "SELECT * FROM vw_livros_por_autor", nativeQuery = true)
    List<LivroPorAutorProjection> findAllFromView();
}
