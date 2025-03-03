package com.dtos;

import jakarta.validation.constraints.NotNull;

public record PostRecordDto(@NotNull String descricao, @NotNull String dataPost) {

}
