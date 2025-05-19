package com.br.tjrj.service;

import com.br.tjrj.exception.InvalidFieldException;
import com.br.tjrj.model.Assunto;
import com.br.tjrj.repository.AssuntoRepository;
import com.br.tjrj.service.message.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Serviço do Assunto
 * para operações de banco de dados
 *  @author guilherme-oliveria
 */
@Service
public class AssuntoService extends GenericServiceAbstract<Assunto,Long> {

    private final AssuntoRepository repository;
    private final MessageService messageService;

    public AssuntoService(AssuntoRepository repository, MessageService messageService) {
        super(repository,messageService);
        this.repository = repository;
        this.messageService = messageService;
    }

    @Override
    public void validateBeforeSave(Assunto entity) {
        validateDescricao(entity);
    }

    @Override
    public void validateBeforeUpdate(Assunto entity) {
       validateDescricao(entity);
    }

    public void validateDescricao(Assunto assunto) throws InvalidFieldException {
        Optional<Assunto> existingAssunto = repository.findByDescricao(assunto.getDescricao());
        if (existingAssunto.isPresent() && !existingAssunto.get().getId().equals(assunto.getId())) {
            throw new InvalidFieldException(HttpStatus.CONFLICT, messageService.getMessage("error.duplicateDescricao"));
        }
    }
}
