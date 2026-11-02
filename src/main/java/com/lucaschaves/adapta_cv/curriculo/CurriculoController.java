package com.lucaschaves.adapta_cv.curriculo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/curriculos")
public class CurriculoController {

    @PostMapping
    public CurriculoRequest criar(@Valid @RequestBody CurriculoRequest curriculo) {
        return curriculo;
    }

    
}
