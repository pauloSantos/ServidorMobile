package com.dieta.vida.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="alimento")
public class AlimentoModel {

	public AlimentoModel(){}
	
	@Id
	@GeneratedValue
	@Column(name="id_alimento")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_refeicao")
	private RefeicaoModel refeicao;
	
	@Column(name="alimento")
	private String nomeAlimento;
	
	@Column(name="quantidade")
	private int quantidadeAlimento;
	
	@Column(name="unidade_de_medida")
	private String unidadeDeMedida;

	public AlimentoModel(String nomeAlimento, int quantidadeAlimento, String unidadeDeMedida){
		this.nomeAlimento = nomeAlimento;
		this.quantidadeAlimento = quantidadeAlimento;
		this.unidadeDeMedida = unidadeDeMedida;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RefeicaoModel getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(RefeicaoModel refeicao) {
		this.refeicao = refeicao;
	}

	public String getNomeAlimento() {
		return nomeAlimento;
	}

	public void setNomeAlimento(String nomeAlimento) {
		this.nomeAlimento = nomeAlimento;
	}

	public int getQuantidadeAlimento() {
		return quantidadeAlimento;
	}

	public void setQuantidadeAlimento(int quantidadeAlimento) {
		this.quantidadeAlimento = quantidadeAlimento;
	}

	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	public void setUnidadeDeMedida(String unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}
	
}
