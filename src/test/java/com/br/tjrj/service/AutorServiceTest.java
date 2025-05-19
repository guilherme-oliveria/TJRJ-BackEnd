package com.br.tjrj.service;

import com.br.tjrj.exception.InvalidFieldException;
import com.br.tjrj.model.Autor;
import com.br.tjrj.model.AutorFactory;
import com.br.tjrj.repository.AutorRepository;
import com.br.tjrj.service.message.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.br.tjrj.model.AutorFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutorServiceTest {

    @Mock
    private AutorRepository repository;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private AutorService autorService;

    @Test
    public void testSave() {
        Autor autor = createAutorWithNome("Autor Teste").build();
        when(repository.save(autor)).thenReturn(autor);

        Autor result = autorService.save(autor);
        assertEquals(autor, result);
        verify(repository).save(autor);
    }

    @Test
    public void testUpdate() throws InvalidFieldException {
        Autor autor = createAutorWithId(1L);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.save(any(Autor.class))).thenReturn(autor);

        Autor result = autorService.update(autor);
        assertEquals(autor, result);
        verify(repository).existsById(autor.getId());
        verify(repository).save(autor);
    }

    @Test
    public void testDelete() {
        doNothing().when(repository).deleteById(any());
        when(repository.existsById(any())).thenReturn(true);

        autorService.delete(1L);
        verify(repository, times(1)).deleteById(any());
    }

    @Test
    public void testFindById() {
        Autor autor = createAutor();
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.of(autor));

        Optional<Autor> result = autorService.findById(1L);
        assertEquals(Optional.of(autor), result);
    }

    @Test
    public void testFindAll() {
        Autor autor = createAutor();
        when(repository.findAll()).thenReturn(List.of(autor));

        assertEquals(List.of(autor), autorService.findAll());
    }

    @Test
    public void testValidateNomeWithDuplicateName() {
        Autor autor = createAutorWithNome("Autor Duplicado").build();
        Autor existingAutor = createAutorWithNome("Autor Duplicado").id(2L).build();

        when(repository.findByNome(autor.getNome())).thenReturn(Optional.of(existingAutor));
        when(messageService.getMessage("error.duplicateNome")).thenReturn("Já existe um autor com o mesmo nome");

        InvalidFieldException exception = assertThrows(InvalidFieldException.class, () -> autorService.validateNome(autor));

        assertEquals("Já existe um autor com o mesmo nome", exception.getMessage());
    }
}