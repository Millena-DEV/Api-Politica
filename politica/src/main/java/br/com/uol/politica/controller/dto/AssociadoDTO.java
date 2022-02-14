package br.com.uol.politica.controller.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.uol.politica.modelo.Associado;
import br.com.uol.politica.modelo.Partido;
import br.com.uol.politica.modelo.StatusPolitico;
import br.com.uol.politica.modelo.StatusSexo;

public class AssociadoDTO {

	private long id;
	private String nome;
	private StatusPolitico cargoPolitico;
	private StatusSexo sexo;
	private LocalDate dataNascimento;
	
	
	public AssociadoDTO (Associado associado) {
		this.nome =associado.getNome();
		this.id = associado.getId();
		this.cargoPolitico= associado.getCargoPolitico();
		this.sexo=associado.getSexo();
		this.dataNascimento = associado.getDatanascimento();
		
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
	public StatusPolitico getCargoPolitico() {
		return cargoPolitico;
	}
	public void setCargoPolitico(StatusPolitico cargoPolitico) {
		this.cargoPolitico = cargoPolitico;
	}
	public StatusSexo getSexo() {
		return sexo;
	}
	public void setSexo(StatusSexo sexo) {
		this.sexo = sexo;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	public static Page<AssociadoDTO> converter(Page<Associado> associado) {
		return associado.map(AssociadoDTO::new);
		
	}
	
}
