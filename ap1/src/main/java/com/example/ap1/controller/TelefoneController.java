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
import org.springframework.web.bind.annotation.RestController;

import com.azure.core.annotation.Post;
import com.example.ap1.exception.TelefoneException;
import com.example.ap1.model.Pessoa;
import com.example.ap1.model.Telefone;
import com.example.ap1.service.PessoaService;
import com.example.ap1.service.TelefoneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa/{idPessoa}/telefone")
@CrossOrigin
class resourceNameController {

    @Autowired
    TelefoneService telefoneService;

    @Autowired PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Telefone>> getAll(@PathVariable("idPessoa") long idPessoa) {
        try {
            Optional<Pessoa> opPessoa = this.pessoaService.findById(idPessoa);

            if (opPessoa.isPresent() == false) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
            }

            return new ResponseEntity<>(opPessoa.get().getTelefones(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Telefone> getById(@PathVariable("id") long id) {
        Optional<Telefone> existingItemOptional = telefoneService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Telefone> create(@PathVariable("idPessoa") long idPessoa, @RequestBody Telefone item) throws TelefoneException, Exception {
        Telefone savedItem = telefoneService.create(idPessoa, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Telefone> update(@PathVariable("id") long id, @RequestBody Telefone item) throws TelefoneException {
        return new ResponseEntity<>(telefoneService.update(id, item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws TelefoneException {
        telefoneService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
