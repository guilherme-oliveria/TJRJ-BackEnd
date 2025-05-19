package com.br.tjrj.dto;

import com.br.tjrj.dto.interfaces.EntityDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorDTO(
        Long id,

        @NotBlank(message = "notBlank.nome")
        @Size(min = 3, max = 40, message = "error.autor.invalidNome.length")
        String nome
) implements EntityDTO {

    @Override
    public Long getId() {
        return this.id;
    }
}
