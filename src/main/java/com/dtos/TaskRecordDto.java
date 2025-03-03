package com.dtos;

import jakarta.validation.constraints.NotBlank;

public record TaskRecordDto(@NotBlank String descricao) {

}
