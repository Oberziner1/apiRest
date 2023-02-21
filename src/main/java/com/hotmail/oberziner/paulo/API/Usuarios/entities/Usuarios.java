package com.hotmail.oberziner.paulo.API.Usuarios.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity (name="Usuario")
@Table (name="Usuario")
public class Usuarios implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;
    
    @Column(name ="nome")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dt_nasc")
    private LocalDate dtNasc;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_endereco_usuario"))
	@JsonManagedReference	
	private List<Endereco> enderecoList = new ArrayList<>();

	public void addEndereco(Endereco endereco) {
		enderecoList.add(endereco);
	}

	
	public void removerEndereco(Endereco endereco) {
		enderecoList.remove(endereco);
	}
    public void Usuario(){}

    public void Usuario(Long idUsuario, String nome, LocalDate dtNasc, List<Endereco> enderecoList) {
		
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.dtNasc = dtNasc;
		this.enderecoList = enderecoList;
	}


    public Long getIdUsuario() {
        return idUsuario;
    }


    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public LocalDate getDtNasc() {
        return dtNasc;
    }


    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }


    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }


    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }


    
}
