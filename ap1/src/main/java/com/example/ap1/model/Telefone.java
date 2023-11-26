package com.example.ap1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name= "telefones")
public class Telefone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 200, nullable = false)
    @Pattern(regexp = "\\d{2}", message = "código país não está em um formato válido" )
    private String codigoPais;

    @Column(length = 200, nullable = false)
    @Pattern(regexp = "\\d{2}", message = "código área não está em um formato válido" )
    private String codigoArea;

    @Column(length = 200, nullable = false)
    @Pattern(regexp = "\\d{8}|\\d{9}", message = "número não está em um formato válido" )
    private String numero;

    @ManyToOne
    @JsonIgnore // ignora a propriedade 
    private Pessoa pessoa; // esse telefone pertence a uma determinada pessoa.

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
