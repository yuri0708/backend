package com.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CommentRecordDto(@NotNull String texto, @NotNull String hora, @NotNull UUID post) {

}
