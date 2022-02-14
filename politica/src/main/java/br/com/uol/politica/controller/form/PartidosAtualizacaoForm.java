package br.com.uol.politica.controller.form;

import java.time.LocalDate;


import br.com.uol.politica.modelo.Partido;
import br.com.uol.politica.repository.PartidosRepository;

public class PartidosAtualizacaoForm {
	
	private String nome;
	private String sigla;
	private String ideologia;
	private LocalDate datafundacao;

	public Partido atualizar(Long id, PartidosRepository pr) {
		Partido partido = pr.getOne(id);
		partido.setNome(this.nome);
		partido.setSigla(this.sigla);
		partido.setIdeologia(this.ideologia);
		partido.setDataFundacao(this.datafundacao);
		
		return partido;
		
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

	public LocalDate getDatafundacao() {
		return datafundacao;
	}

	public void setDatafundacao(LocalDate datafundacao) {
		this.datafundacao = datafundacao;
	}


	

}
