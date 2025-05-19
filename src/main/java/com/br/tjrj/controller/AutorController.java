package com.br.tjrj.controller;

import com.br.tjrj.dto.AutorDTO;
import com.br.tjrj.mapper.AutorMapper;
import com.br.tjrj.model.Autor;
import com.br.tjrj.service.AutorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/autor")
@Validated
@Tag(name = "Autor", description = "Operações relacionadas aos autores")
public class AutorController extends AbstractController<Autor, AutorDTO, Long, AutorService, AutorMapper> {

    public AutorController(AutorService service, AutorMapper mapper) {
        super(service, mapper);
    }

}
