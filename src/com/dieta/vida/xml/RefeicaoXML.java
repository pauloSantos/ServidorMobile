package com.dieta.vida.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RefeicaoXML {

	@XmlElement
	@XmlElementWrapper(name="alimentos")
	private List<AlimentoXML> alimento;
	
	@XmlElement(name="tipo")
	private String tipoRefeicao;
	
	@XmlElement
	private String horario;

	public List<AlimentoXML> getAlimento() {
		return alimento;
	}

	public void setAlimento(List<AlimentoXML> alimento) {
		this.alimento = alimento;
	}

	public String getTipoRefeicao() {
		return tipoRefeicao;
	}

	public void setTipoRefeicao(String tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String periodo) {
		this.horario = periodo;
	}
	
}
