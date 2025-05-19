package com.br.tjrj.service;

import com.br.tjrj.exception.InvalidFieldException;
import com.br.tjrj.model.*;
import com.br.tjrj.repository.LivroRepository;
import com.br.tjrj.service.message.MessageService;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static com.br.tjrj.model.LivroPorAutorProjectionFactory.createLivroPorAutorProjection;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private LivroService livroService;

    @Test
    public void testValidateBeforeSave() {
        Livro livro = Livro.builder().titulo("Livro Teste").autores(List.of(Autor.builder().build())).assuntos(List.of(Assunto.builder().build())).build();
        when(repository.findByTitulo("Livro Teste")).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> livroService.validateBeforeSave(livro));
        verify(repository).findByTitulo("Livro Teste");
    }

    @Test
    public void testValidateBeforeUpdate() {
        Livro livro = Livro.builder().id(1L).titulo("Livro Teste").autores(List.of(Autor.builder().build())).assuntos(List.of(Assunto.builder().build())).build();
        when(repository.findByTitulo("Livro Teste")).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> livroService.validateBeforeSave(livro));
        verify(repository).findByTitulo("Livro Teste");
    }

    @Test
    public void testValidateTituloWithDuplicateTitulo() {
        Livro livro = Livro.builder().id(1L).titulo("Livro Duplicado").build();
        Livro existingLivro = Livro.builder().id(2L).titulo("Livro Duplicado").build();

        when(repository.findByTitulo(livro.getTitulo())).thenReturn(Optional.of(existingLivro));
        when(messageService.getMessage("error.duplicateTitulo")).thenReturn("Já existe um cadastro com o mesmo titulo");

        InvalidFieldException exception = assertThrows(InvalidFieldException.class, () -> livroService.validateTitulo(livro));

        assertEquals("Já existe um cadastro com o mesmo titulo", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getErrorCode());
    }

    @Test
    public void testValidateTituloWithoutDuplicateTitulo() {
        Livro livro = Livro.builder().id(1L).titulo("Livro Único").build();

        when(repository.findByTitulo(livro.getTitulo())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> livroService.validateTitulo(livro));
    }

    @Test
    public void testGerarRelatorioLivrosPorAutor() throws JRException {
        LivroPorAutorProjection projection = createLivroPorAutorProjection();

        List<LivroPorAutorProjection> projections = List.of(projection);
        when(repository.findAllFromView()).thenReturn(projections);

        byte[] pdf = livroService.gerarRelatorioLivrosPorAutor();
        assertNotNull(pdf);
        assertTrue(pdf.length > 0);
    }
}