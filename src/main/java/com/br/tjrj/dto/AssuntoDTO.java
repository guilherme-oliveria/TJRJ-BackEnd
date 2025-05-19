package com.br.tjrj.dto;

import com.br.tjrj.dto.interfaces.EntityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AssuntoDTO(
        Long id,

        @NotBlank(message = "notBlank.descricao")
        @Size(min = 3, max = 20, message = "error.assunto.invalidDescricao.length")
        String descricao
) implements EntityDTO {

    @Override
    public Long getId() {
        return this.id;
    }

}
