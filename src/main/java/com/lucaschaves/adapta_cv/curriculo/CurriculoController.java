package com.lucaschaves.adapta_cv.curriculo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/curriculos")
public class CurriculoController {

    private final CurriculoRepository repository;
    private final LeitorPdf leitorPdf;

    public CurriculoController(CurriculoRepository repository, LeitorPdf leitorPdf) {
        this.repository = repository;
        this.leitorPdf = leitorPdf;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CurriculoResponse criar(@Valid @RequestBody CurriculoRequest request) {
        Curriculo curriculo = new Curriculo(request.nome(), request.resumo(), request.experiencias());
        Curriculo salvo = repository.save(curriculo);
        return CurriculoResponse.de(salvo);
    }

    @GetMapping
    public List<CurriculoResponse> listar() {
        return repository.findAll()
            .stream()
            .map(CurriculoResponse::de)
            .toList();
    }

    @GetMapping("/{id}")
    public CurriculoResponse buscar(@PathVariable Long id) {
        Curriculo curriculo = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curriculo não encontrado"));
            return CurriculoResponse.de(curriculo);
    }
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
    @ResponseStatus(HttpStatus.CREATED)
        public CurriculoResponse upload(@RequestParam("arquivo") MultipartFile arquivo, @RequestParam("nome") String nome) {
        String texto = leitorPdf.extrairTexto(arquivo);
        Curriculo curriculo = new Curriculo(nome, texto);
        return CurriculoResponse.de(repository.save(curriculo));
    }

}
