package com.dtos;

import jakarta.validation.constraints.NotNull;

public record AlbumRecordDto(@NotNull String nome, @NotNull String descricao) {

}
