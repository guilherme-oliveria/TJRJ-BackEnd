package com.br.tjrj.controller;

import com.br.tjrj.dto.AssuntoDTO;
import com.br.tjrj.mapper.AssuntoMapper;
import com.br.tjrj.model.Assunto;
import com.br.tjrj.service.AssuntoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assunto")
@Validated
@Tag(name = "Assunto", description = "Operações relacionadas aos assuntos")
public class AssuntoController extends AbstractController<Assunto, AssuntoDTO, Long, AssuntoService, AssuntoMapper> {

    public AssuntoController(AssuntoService service, AssuntoMapper mapper) {
        super(service, mapper);
    }

}
