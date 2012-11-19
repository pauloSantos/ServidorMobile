package com.dieta.vida.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.site.business.enums.Genero;
import com.site.business.enums.StatusDieta;

@Entity()
@Table(name="download_dieta", schema="mobile")
public class DownloadDietaModel {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="id_imei")
	private Integer imei;
	
	@Column(name="data_solicitacao")
	private Date dataSolicitacao;
	
	@Column(name="data_ultima_atualizacao")
	private Date dataUltimaAtualizacao;
	
	@Column(name="nome")
	private String nomeUsuario;
	
	@Column(name="idade")
	private int idadeUsuario;
	
	@Column(name="genero")
	@Enumerated(EnumType.STRING)
	private Genero generoUsuario;
	
	@Column(name="altura")
	private double alturaUsuario;
	
	@Column(name="peso")
	private double pesoUsuario;
	
	@Column(name="email")
	private String emailUsuario;
	
	@Column(name="status_dieta")
	@Enumerated(EnumType.STRING)
	private StatusDieta statusDieta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getImei() {
		return imei;
	}
	public void setImei(Integer imei) {
		this.imei = imei;
	}
	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public int getIdadeUsuario() {
		return idadeUsuario;
	}
	public void setIdadeUsuario(int idadeUsuario) {
		this.idadeUsuario = idadeUsuario;
	}
	public Genero getGeneroUsuario() {
		return generoUsuario;
	}
	public void setGeneroUsuario(Genero generoUsuario) {
		this.generoUsuario = generoUsuario;
	}
	public double getAlturaUsuario() {
		return alturaUsuario;
	}
	public void setAlturaUsuario(double alturaUsuario) {
		this.alturaUsuario = alturaUsuario;
	}
	public double getPesoUsuario() {
		return pesoUsuario;
	}
	public void setPesoUsuario(double pesoUsuario) {
		this.pesoUsuario = pesoUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public StatusDieta getStatusDieta() {
		return statusDieta;
	}
	public void setStatusDieta(StatusDieta statusDieta) {
		this.statusDieta = statusDieta;
	}
}
