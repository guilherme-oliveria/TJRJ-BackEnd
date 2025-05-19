package com.br.tjrj.controller;

import com.br.tjrj.dto.LivroDTO;
import com.br.tjrj.mapper.LivroMapper;
import com.br.tjrj.model.Livro;
import com.br.tjrj.service.LivroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para livro
 * para operações de banco de dados
 * {@link RestController}
 */
@RestController
@RequestMapping("/api/v1/livro")
@Validated
@Tag(name = "Livro", description = "Operações relacionadas aos livros")
public class LivroController extends AbstractController<Livro, LivroDTO, Long, LivroService, LivroMapper> {

    public LivroController(LivroService service, LivroMapper mapper) {
        super(service, mapper);
    }

    @GetMapping("/relatorio/livros-por-autor")
    public ResponseEntity<byte[]> gerarRelatorio() throws JRException {
        byte[] pdf = service.gerarRelatorioLivrosPorAutor();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=livros_por_autor.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
