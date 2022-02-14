package br.com.uol.politica.controller.form;

import java.time.LocalDate;

import br.com.uol.politica.modelo.Associado;
import br.com.uol.politica.modelo.StatusPolitico;
import br.com.uol.politica.modelo.StatusSexo;
import br.com.uol.politica.repository.AssociadosRepository;

public class AssociadosAtualizacaoForm {
	
	private String nome;
	private StatusPolitico cargoPolitico;
	private StatusSexo sexo;
	private LocalDate dataNascimento;
	
	
	public Associado Atualizar (long id, AssociadosRepository ar) {
		Associado associado =ar.getOne(id);
		associado.setNome(this.nome);
		associado.setCargoPolitico(this.cargoPolitico);
		associado.setSexo(this.sexo);
		associado.setDatanascimento(this.dataNascimento);
		return associado;
		
		
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
	
	
	

}
