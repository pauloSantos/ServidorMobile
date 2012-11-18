package com.dieta.vida.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dieta")
@XmlAccessorType(XmlAccessType.FIELD)
public class DietaXML {

	@XmlElement
	private Long identificacaoDieta;
	
	@XmlElement(name="nome")
	private String nomeDieta;
	
	@XmlElement
	private Integer duracao;
	
	@XmlElement
	@XmlElementWrapper(name="refeicoes")
	private List<RefeicaoXML> refeicao;
	
	public Long getIdentificacaoDieta() {
		return identificacaoDieta;
	}

	public void setIdentificacaoDieta(Long id) {
		this.identificacaoDieta = id;
	}

	public String getNomeDieta() {
		return nomeDieta;
	}

	public void setNomeDieta(String nomeDieta) {
		this.nomeDieta = nomeDieta;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public List<RefeicaoXML> getRefeicoes() {
		return refeicao;
	}

	public void setRefeicoes(List<RefeicaoXML> refeicoesPrincipais) {
		this.refeicao = refeicoesPrincipais;
	}
	
}
