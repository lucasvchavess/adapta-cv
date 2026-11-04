package com.lucaschaves.adapta_cv.curriculo;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service 
public class LeitorPdf {

    public String extrairTexto(MultipartFile arquivo) {
        try (PDDocument documento = Loader.loadPDF(arquivo.getBytes())) {
            PDFTextStripper extrator = new PDFTextStripper();
            return extrator.getText(documento);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível ler o arquivo PDF");
        }
    }
}