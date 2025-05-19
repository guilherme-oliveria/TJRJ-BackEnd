package com.br.tjrj.repository;

import com.br.tjrj.model.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para Assunto.
 *
 * @author guilherme-oliveria
 */
@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Long> {

    Optional<Assunto> findByDescricao(String descricao);
}
