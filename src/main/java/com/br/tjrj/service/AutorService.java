package com.br.tjrj.service;

import com.br.tjrj.exception.InvalidFieldException;
import com.br.tjrj.model.Autor;
import com.br.tjrj.repository.AutorRepository;
import com.br.tjrj.service.message.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Serviço do Autor
 * para operações de banco de dados
 *  @author guilherme-oliveria
 */
@Service
public class AutorService extends GenericServiceAbstract<Autor,Long> {

    private final AutorRepository repository;
    private final MessageService messageService;

    public AutorService(AutorRepository repository, MessageService messageService) {
        super(repository,messageService);
        this.repository = repository;
        this.messageService = messageService;
    }

    @Override
    public void validateBeforeSave(Autor entity) {
        validateNome(entity);
    }

    @Override
    public void validateBeforeUpdate(Autor entity) {
        validateNome(entity);
    }

    public void validateNome(Autor autor) throws InvalidFieldException {
        Optional<Autor> existingAutor = repository.findByNome(autor.getNome());
        if (existingAutor.isPresent() && !existingAutor.get().getId().equals(autor.getId())) {
            throw new InvalidFieldException(HttpStatus.CONFLICT, messageService.getMessage("error.duplicateNome"));
        }
    }
}
