package com.lucaschaves.adapta_cv.curriculo;

import java.time.LocalDateTime;
import java.util.List;

public record CurriculoResponse(
    Long id,
    String nome,
    String resumo,
    List<String> experiencias,
    LocalDateTime criadoEm) {


    public static CurriculoResponse de(Curriculo curriculo) {
        return new CurriculoResponse(
            curriculo.getId(),
            curriculo.getNome(),
            curriculo.getResumo(),
            curriculo.getExperiencias(),
            curriculo.getCriadoEm());
        }
    }