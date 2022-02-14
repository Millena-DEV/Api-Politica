package br.com.uol.politica.modelo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;
	@Enumerated(EnumType.STRING)
	private StatusPolitico cargoPolitico = StatusPolitico.NAO_RESPONDIDO;
	@Enumerated(EnumType.STRING)
	private StatusSexo sexo = StatusSexo.NAO_RESPONDIDO;
	private LocalDate datanascimento;
	
	@ManyToOne
	private Partido partido;
	
	

public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
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

	public LocalDate getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(LocalDate datanascimento) {
		this.datanascimento = datanascimento;
	}

	


}
