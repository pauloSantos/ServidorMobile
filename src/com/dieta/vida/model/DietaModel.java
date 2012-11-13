package com.dieta.vida.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.site.model.CadastroMedico;

@Entity
@Table(name="dieta")
public class DietaModel {

	@Id
	@GeneratedValue
	@Column(name="id_dieta")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_cadastro_medico")
	private CadastroMedico medico;
	
	@Column(name="nome_dieta")
	private String nomeDieta;
	
	@Column(name="periodo_dieta")
	private int periodoDieta;

	public Long getId() {
		return id;
	}

	public CadastroMedico getMedico() {
		return medico;
	}

	public void setMedico(CadastroMedico medico) {
		this.medico = medico;
	}

	public String getNomeDieta() {
		return nomeDieta;
	}

	public void setNomeDieta(String nomeDieta) {
		this.nomeDieta = nomeDieta;
	}

	public int getPeriodoDieta() {
		return periodoDieta;
	}

	public void setPeriodoDieta(int periodoDieta) {
		this.periodoDieta = periodoDieta;
	}
	
}
