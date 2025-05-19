package com.br.tjrj.service;

import com.br.tjrj.exception.InvalidFieldException;
import com.br.tjrj.model.Livro;
import com.br.tjrj.model.LivroPorAutorProjection;
import com.br.tjrj.repository.LivroRepository;
import com.br.tjrj.service.message.MessageService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Serviço do Livro
 * para operações de banco de dados
 *  @author guilherme-oliveria
 */
@Service
public class LivroService extends GenericServiceAbstract<Livro,Long> {
    private final LivroRepository repository;
    private final MessageService messageService;

    public LivroService(LivroRepository repository, MessageService messageService) {
        super(repository,messageService);
        this.repository = repository;
        this.messageService = messageService;
    }

    public byte[] gerarRelatorioLivrosPorAutor() throws JRException {
        List<LivroPorAutorProjection> dados = repository.findAllFromView();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/livros_por_autor.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    @Override
    public void validateBeforeSave(Livro entity) {
        validateTitulo(entity);
        validateAutoresELivros(entity);
    }

    @Override
    public void validateBeforeUpdate(Livro entity) {
        validateTitulo(entity);
        validateAutoresELivros(entity);
    }

    public void validateTitulo(Livro livro) throws InvalidFieldException {
        Optional<Livro> existingLivro = repository.findByTitulo(livro.getTitulo());
        if (existingLivro.isPresent() && !existingLivro.get().getId().equals(livro.getId())) {
            throw new InvalidFieldException(HttpStatus.CONFLICT, messageService.getMessage("error.duplicateTitulo"));
        }
    }

    private void validateAutoresELivros(Livro livro) {
        if (livro.getAutores() == null || livro.getAutores().isEmpty()) {
            throw new InvalidFieldException(HttpStatus.BAD_REQUEST, messageService.getMessage("error.invalidAutores.required"));
        }

        if (livro.getAssuntos() == null || livro.getAssuntos().isEmpty()) {
            throw new InvalidFieldException(HttpStatus.BAD_REQUEST, messageService.getMessage("error.invalidAssuntos.required"));
        }
    }
}
