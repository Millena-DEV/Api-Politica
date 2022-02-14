package br.com.uol.politica.controller.dto;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.domain.Page;


import br.com.uol.politica.modelo.Partido;



public class PartidoDTO {

	private long id;
	private String nome;
	private String sigla;
	private String ideologia;
	private LocalDate dataFundacao;
	
	public PartidoDTO( Partido partido) {
		this.id = partido.getId();
		this.nome = partido.getNome();
		this.sigla=partido.getSigla();
		this.ideologia = partido.getIdeologia();
		this.dataFundacao = partido.getDataFundacao();
	}
	


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public LocalDate getDataFundacao() {
		return dataFundacao;
	}
	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}



public static Page<PartidoDTO> converter(Page<Partido> partido) {
	return partido.map(PartidoDTO::new);
	
}
	
}
