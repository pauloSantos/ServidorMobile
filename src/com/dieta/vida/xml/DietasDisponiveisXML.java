package com.dieta.vida.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dietasDisponiveis")
@XmlAccessorType(XmlAccessType.FIELD)
public class DietasDisponiveisXML {

	@XmlElementWrapper(name="dietas")
	@XmlElement(name="dieta")
	private List<DietaXML> listaDietasXML;

	public List<DietaXML> getListaDietasXML() {
		return listaDietasXML;
	}

	public void setListaDietasXML(List<DietaXML> dietas) {
		this.listaDietasXML = dietas;
	}
}
