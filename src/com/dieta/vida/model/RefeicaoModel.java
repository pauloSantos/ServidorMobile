package com.dieta.vida.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.site.business.enums.OpcoesRefeicao;

@Entity
@Table(name="refeicao")
public class RefeicaoModel {

	@Id
	@GeneratedValue
	@Column(name="id_refeicao")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_dieta")
	private DietaModel dieta;
	
	@Column(name="horario")
	private String horarioRefeicao;
	
	@Column(name="opcao_refeicao")
	@Enumerated(EnumType.STRING)
	private OpcoesRefeicao opcaoRefeicao;
	
	public Long getId() {
		return id;
	}
	
	public DietaModel getDieta() {
		return dieta;
	}
	
	public void setDieta(DietaModel dieta) {
		this.dieta = dieta;
	}
	
	public String getHorarioRefeicao() {
		return horarioRefeicao;
	}
	
	public void setHorarioRefeicao(String horarioRefeicao) {
		this.horarioRefeicao = horarioRefeicao;
	}
	
	public OpcoesRefeicao getOpcaoRefeicao() {
		return opcaoRefeicao;
	}
	
	public void setOpcoesRefeicao(OpcoesRefeicao opcaoRefeicao) {
		this.opcaoRefeicao = opcaoRefeicao;
	}
}
