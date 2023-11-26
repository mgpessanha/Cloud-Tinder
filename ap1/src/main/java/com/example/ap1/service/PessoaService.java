package com.example.ap1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ap1.exception.PessoaException;
import com.example.ap1.model.Pessoa;
import com.example.ap1.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository _pessoaRepository;

    @Autowired
    private AzureStorageAccountService azureStorageAccountService;
    

    // CRUD

    public List<Pessoa> findAll() { // busca todas as pessoas
        return this._pessoaRepository.findAll();
    }
    //--------------------------------------------------------------

    public Optional<Pessoa> findById(long id) { // busca a pessoa pelo seu id
        return this._pessoaRepository.findById(id);
    }
    //--------------------------------------------------------------

    public Pessoa save(Pessoa pessoa) throws PessoaException { // cria ou salva uma pessoa, o próprio jpa identifica.

        this._pessoaRepository.save(pessoa);
        return pessoa;
    }
    //--------------------------------------------------------------

    public Pessoa update(long id, Pessoa newData) throws PessoaException { // atualiza a pessoa
        Optional<Pessoa> result = this._pessoaRepository.findById(id); // procura a pessoa pelo id

        if (result.isPresent() == false) { // se a pessoa não for encontrada, retorna essa msg
            throw new PessoaException("Não encontrei a pessoa a ser atualizada");
        }

        Pessoa pessoaASerAtualizada = result.get(); // encontrou a pessoa, pega as suas informações e atualiza ela
        pessoaASerAtualizada.setNome(newData.getNome());
        pessoaASerAtualizada.setCpf(newData.getCpf());
        pessoaASerAtualizada.setBio(newData.getBio());
        pessoaASerAtualizada.setDtNascimento(newData.getDtNascimento());

        this._pessoaRepository.save(pessoaASerAtualizada); // salva as atualizações
        return pessoaASerAtualizada; // retorna a pessoa atualizada
    }
    //--------------------------------------------------------------

    public void delete(long id) throws PessoaException { // void pq, como vai ser deletada, não precisa ter nenhum retorno
        Optional<Pessoa> pessoaASerExcluida = this._pessoaRepository.findById(id); // acha a pessoa pelo id
        
        if (pessoaASerExcluida.isPresent() == false) { // se a pessoa não for encontrada, retorna essa msg
            throw new PessoaException("Não encontrei a pessoa a ser deletada");
        }
        this._pessoaRepository.delete(pessoaASerExcluida.get());
    }
    //--------------------------------------------------------------

    public void saveTelefone(Pessoa pessoa) {
        this._pessoaRepository.save(pessoa);
    }
    //--------------------------------------------------------------

    public void uploadFileToPessoa(MultipartFile file, long id) throws PessoaException, Exception {
        
        Optional<Pessoa> opPessoa = this._pessoaRepository.findById(id);
        
        if (opPessoa.isPresent() == false) {
            throw new PessoaException("Não encontrei a pessoa para associar a imagem");
        }

        Pessoa pessoa = opPessoa.get(); // pega a pessoa
        String ulrImage = this.azureStorageAccountService.uploadFileToAzure(file); // sobe a imagem pro azure
        pessoa.setUrlImage(ulrImage); // pega o endereço que o azure fornece e associa a pessoa a imagem no banco de dados
        this._pessoaRepository.save(pessoa); // atualiza a pessoa com uma imagem dela no banco de dados
    }

}