package com.dtos;

import java.util.UUID;


import jakarta.validation.constraints.NotNull;

public record PhotoRecordDto(@NotNull String descricao, @NotNull UUID album) {

}
