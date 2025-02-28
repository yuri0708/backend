package com.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto(@NotBlank String nome, @NotNull String email, @NotNull String senha, @NotNull String telefone) {

}
