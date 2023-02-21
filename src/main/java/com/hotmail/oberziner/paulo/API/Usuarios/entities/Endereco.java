package com.hotmail.oberziner.paulo.API.Usuarios.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Endereco implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEndereco;

    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name ="fk_endereco_usuario"))
	@JsonBackReference
	private final Usuarios usuario;

    private String cep;
	private String logradouro;
	private String cidade;
	private Long numero;

	public void Endereco() {}

    public Endereco(Long idEndereco, Usuarios usuario, String cep, String logradouro, String cidade, Long numero) {
        this.idEndereco = idEndereco;
        this.usuario = usuario;
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.numero = numero;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    
}
