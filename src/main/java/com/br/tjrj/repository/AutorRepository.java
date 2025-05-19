package com.br.tjrj.repository;

import com.br.tjrj.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para Autor.
 *
 * @author guilherme-oliveria
 */
@Repository
public interface AutorRepository  extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

}
