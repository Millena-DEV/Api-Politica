package br.com.uol.politica.modelo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
public class Partido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull 
	private String nome;
	@NotNull
	private String sigla;
	@NotNull
	private String ideologia;
	@NotNull
	private LocalDate dataFundacao;
	
	@OneToMany
	private List <Associado> associado;
	
	public Partido() {
		
	}

	public Partido(String nome, String sigla, String ideologia,  LocalDate datafundacao, Partido partido) {
		this.nome=nome;
		this.sigla=sigla;
		this.ideologia=ideologia;
		this.dataFundacao=datafundacao;
	}

	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getIdeologia() {
		return ideologia;
	}
	public void setIdeologia(String ideologia) {
		this.ideologia = ideologia;
	}
	public  LocalDate getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao( LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	}



	
	
	
	

