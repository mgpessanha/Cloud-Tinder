package com.example.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ap1.model.Telefone;
import com.example.ap1.exception.TelefoneException;
import com.example.ap1.model.Pessoa;
import com.example.ap1.repository.TelefoneRepository;

@Service
public class TelefoneService {
    @Autowired
    TelefoneRepository telefoneRepository;

    @Autowired
    PessoaService pessoaService;

    // CRUD

    public List<Telefone> findAll() {
        return this.telefoneRepository.findAll();
    }

    public Optional<Telefone> findById(long id) {
        return this.telefoneRepository.findById(id);
    }

    public Telefone create(long idPessoa, Telefone newTelefone) throws TelefoneException, Exception {
        Optional<Pessoa> opPessoa = this.pessoaService.findById(idPessoa);

        if (opPessoa.isPresent() == false) {
            throw new TelefoneException("Não encontrei a pessoa para adicionar esse telefone");
        }

        Pessoa pessoa = opPessoa.get(); // insere na tabela de telefone os dados e seta que o telefone pertence a uma determinada pessoa.
        pessoa.addTelefone(newTelefone);
        this.pessoaService.save(pessoa); //  salva o telefone

        Telefone result = pessoa.getTelefones().get(pessoa.getTelefones().size() - 1);
        return result;
    }

    public Telefone update(long id, Telefone newData) throws TelefoneException {
        Optional<Telefone> existingItemOptional = telefoneRepository.findById(id);

        if (existingItemOptional.isPresent() == false)
            throw new TelefoneException("Não encontrei o telefone a ser atualizado");

        Telefone existingItem = existingItemOptional.get();

        existingItem.setCodigoPais(newData.getCodigoPais());
        existingItem.setCodigoArea(newData.getCodigoArea());
        existingItem.setNumero(newData.getNumero());

        telefoneRepository.save(existingItem);
        return existingItem;
    }

    public void delete(long id) throws TelefoneException {
        Optional<Telefone> telefone = this.telefoneRepository.findById(id);

        if (telefone.isPresent() == false)
            throw new TelefoneException("Não encontrei o telefone a ser deletado");

        this.telefoneRepository.delete(telefone.get());
    }

}