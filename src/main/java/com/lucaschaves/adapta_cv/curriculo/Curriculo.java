package com.lucaschaves.adapta_cv.curriculo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity 
@Table(name = "curriculos")
public class Curriculo {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String resumo;

    @Column(columnDefinition = "TEXT")
    private String textoOriginal;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "curriculo_experiencias", joinColumns = @JoinColumn(name = "curriculo_id"))
    @Column(name = "descricao", columnDefinition = "TEXT")
    private List<String> experiencias = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private String nome;

    protected Curriculo() {
    }

    public Curriculo(String nome, String resumo, List<String> experiencias) {
        this.nome = nome;
        this.resumo = resumo;
        this.experiencias = experiencias;
        this.criadoEm = LocalDateTime.now();


    }

    public Curriculo(String nome, String textoOriginal) {
        this.nome = nome;
        this.textoOriginal = textoOriginal;
        this.criadoEm = LocalDateTime.now();
    }

    

    public Long getId() {
        return id;
    }

    public String getResumo() {
        return resumo;
    }

    public List<String> getExperiencias() {
        return experiencias;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public String getNome() {
        return nome;
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    
}