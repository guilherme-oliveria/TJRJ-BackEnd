package com.br.tjrj.service;

import com.br.tjrj.exception.InvalidFieldException;
import com.br.tjrj.model.Assunto;
import com.br.tjrj.repository.AssuntoRepository;
import com.br.tjrj.service.message.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static com.br.tjrj.model.AssuntoFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssuntoServiceTest {

    @Mock
    private AssuntoRepository repository;
    @Mock
    private MessageService messageService;
    @InjectMocks
    private AssuntoService assuntoService;


    @Test
    public void testValidateBeforeSave() {
        Assunto assunto = createAssuntoWithDescricao("Assunto Teste").build();
        when(repository.findByDescricao("Assunto Teste")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> assuntoService.validateBeforeSave(assunto));
        verify(repository).findByDescricao("Assunto Teste");
    }

    @Test
    public void testValidateBeforeUpdate() {
        Assunto assunto = createAssuntoWithDescricao("Assunto Teste").build();
        when(repository.findByDescricao("Assunto Teste")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> assuntoService.validateBeforeUpdate(assunto));
        verify(repository).findByDescricao("Assunto Teste");
    }

    @Test
    public void testValidateDescricaoWithDuplicateDescricao() {
        Assunto assunto = createAssuntoWithDescricao("Assunto Duplicado").build();
        Assunto existingAssunto = createAssuntoWithDescricao("Assunto Duplicado").id(2L).build();

        when(repository.findByDescricao(assunto.getDescricao())).thenReturn(Optional.of(existingAssunto));
        when(messageService.getMessage("error.duplicateDescricao")).thenReturn("Descrição duplicada");

        InvalidFieldException exception = assertThrows(InvalidFieldException.class, () -> assuntoService.validateDescricao(assunto));

        assertEquals("Descrição duplicada", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getErrorCode());
    }

    @Test
    public void testValidateDescricaoWithoutDuplicateDescricao() {
        Assunto assunto = createAssuntoWithDescricao("Assunto Único").build();

        when(repository.findByDescricao(assunto.getDescricao())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> assuntoService.validateDescricao(assunto));
    }

    @Test
    public void testSave() {
        Assunto assunto = createAssuntoWithDescricao("Assunto Teste").build();
        when(repository.save(assunto)).thenReturn(assunto);

        Assunto result = assuntoService.save(assunto);
        assertEquals(assunto, result);
        verify(repository).save(assunto);
    }

    @Test
    public void testUpdate() throws InvalidFieldException {
        Assunto assunto = createAssuntoWithId(1L);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.save(any(Assunto.class))).thenReturn(assunto);

        Assunto result = assuntoService.update(assunto);
        assertEquals(assunto, result);
        verify(repository).existsById(assunto.getId());
        verify(repository).save(assunto);
    }

    @Test
    public void testDelete() {
        doNothing().when(repository).deleteById(any());
        when(repository.existsById(any())).thenReturn(true);

        assuntoService.delete(1L);
        verify(repository, times(1)).deleteById(any());
    }

    @Test
    public void testFindById() {
        Assunto assunto = createAssunto();
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.of(assunto));

        Optional<Assunto> result = assuntoService.findById(1L);
        assertEquals(Optional.of(assunto), result);
    }

    @Test
    public void testFindAll() {
        Assunto assunto = createAssunto();
        when(repository.findAll()).thenReturn(List.of(assunto));

        assertEquals(List.of(assunto), assuntoService.findAll());
    }
}