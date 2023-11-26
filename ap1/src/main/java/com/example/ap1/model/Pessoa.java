package com.example.ap1.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "pessoas")
public class Pessoa {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // identificação da tabela
    private long id;

    @Column(nullable = false) // pro banco saber que a coluna não aceita nulo
    @NotBlank(message = "O campo nome não pode ser vazio") // msg para o usuario dizendo que o campo não pode ser nulo
    private String nome;

    @Column(nullable = true)
    @NotBlank(message = "O campo cpf não pode ser vazio")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "O cpf não está em um formato válido" ) // expressao regular para definir como deve ser inserido o cpf
    private String cpf;

    @Column(nullable = true)
    private LocalDateTime dtNascimento;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String urlImage;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) // cascade - quando deletar uma pessoa, deleta todos os telefones. fech lazy - só carrega os telefones se for pedido em determinado trecho do codigo, diferentemente do eager que ja carrega tudo na hr.
    @JoinColumn(name = "pessoa_id") // especifica que ele vai pegar os telefones por essa chave.
    private List<Telefone> telefones;

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDateTime dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void addTelefone(Telefone telefone) {
        this.telefones.add(telefone);
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}