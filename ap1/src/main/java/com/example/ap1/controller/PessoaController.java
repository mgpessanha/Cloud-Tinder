package com.example.ap1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ap1.exception.PessoaException;
import com.example.ap1.model.Pessoa;
import com.example.ap1.service.PessoaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
@Tag(name = "Pessoa", description = "")
@CrossOrigin
public class PessoaController {

    @Autowired
    private PessoaService _pessoaService;

    @GetMapping // retorna todas as pessoas
    public ResponseEntity<List<Pessoa>> getAll() {
        try {
            return new ResponseEntity<>(this._pessoaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping // cria a pessoa
    public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa item) throws PessoaException{ // valida a requisição
        Pessoa result = this._pessoaService.save(item);
        return new ResponseEntity<>(result, HttpStatus.CREATED);   
    }

    @GetMapping("{id}") // retorna a pessoa por id
    public ResponseEntity<Pessoa> getById(@PathVariable("id") long id) {

        Optional<Pessoa> result = this._pessoaService.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } 
            
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PutMapping("{id}") // atualiza a pessoa
    public ResponseEntity<Pessoa> update(@PathVariable("id") long id, @Valid @RequestBody Pessoa pessoaNovosDados) throws PessoaException{ // valida o dado que esta sendo enviado  
        Pessoa result = this._pessoaService.update(id, pessoaNovosDados);
        return new ResponseEntity<>(result, HttpStatus.OK); 
    }

    @DeleteMapping("{id}") // deleta a pessoa
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws PessoaException{
        this._pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}")
    public ResponseEntity<String> uploadPessoaImage(@PathVariable("id") long id, @RequestParam("file") MultipartFile file) throws PessoaException, Exception{
        _pessoaService.uploadFileToPessoa(file, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}