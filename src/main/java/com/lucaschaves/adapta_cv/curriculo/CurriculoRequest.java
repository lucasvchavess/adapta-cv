package com.lucaschaves.adapta_cv.curriculo;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CurriculoRequest(
        @NotBlank String nome,
        @NotBlank String resumo,
        @NotEmpty List<String> experiencias) {

}
