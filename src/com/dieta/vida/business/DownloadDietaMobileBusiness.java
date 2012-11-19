package com.dieta.vida.business;

import java.util.Date;

import com.dieta.vida.dao.DownloadDietaMobileDAO;
import com.dieta.vida.model.DownloadDietaModel;
import com.dieta.vida.model.Perfil;
import com.google.gson.Gson;
import com.site.business.enums.Genero;
import com.site.business.enums.StatusDieta;

public class DownloadDietaMobileBusiness implements IDownloadDietaMobileBusiness{

	@Override
	public void criarDownloadDieta(String perfilJson) {
		DownloadDietaMobileDAO downloadDietaDAO = new DownloadDietaMobileDAO();
		Gson gson = new Gson();
		Perfil perfil = gson.fromJson(perfilJson, Perfil.class);
		Date hoje = new Date();

		DownloadDietaModel downloadDietaAntigo = downloadDietaDAO.encontrarDownloadPorIdentificacaoEStatus(perfil.getIdPerfil(), StatusDieta.FAZENDO);
		
		if(downloadDietaAntigo != null){
			downloadDietaAntigo.setStatusDieta(StatusDieta.NAO_FEITA);
			downloadDietaAntigo.setDataUltimaAtualizacao(hoje);
			downloadDietaDAO.atualizar(downloadDietaAntigo);
		}

		DownloadDietaModel downloadDieta = new DownloadDietaModel();
		StatusDieta statusDieta = StatusDieta.FAZENDO;

		DownloadDietaModel downloadDietaPreenchido = preencherDownloadDieta(downloadDieta, perfil, statusDieta);
		downloadDietaPreenchido.setDataSolicitacao(hoje);
		
		downloadDietaDAO.inserir(downloadDietaPreenchido);

		downloadDietaDAO.getEntityManager().getEntityManagerFactory().close();

	}

	@Override
	public void atualizarDownloadDieta(String perfilJson, String statusDietaStr) {
		DownloadDietaMobileDAO downloadDietaDAO = new DownloadDietaMobileDAO();
		Gson gson = new Gson();
		Perfil perfil = gson.fromJson(perfilJson, Perfil.class);
		StatusDieta status = getStatusDieta(statusDietaStr);

		DownloadDietaModel downloadDietaAntigo = downloadDietaDAO.encontrarDownloadPorIdentificacaoEStatus(perfil.getIdPerfil(), StatusDieta.FAZENDO);
		DownloadDietaModel downloadDieta = preencherDownloadDieta(downloadDietaAntigo, perfil, status);
		downloadDietaDAO.atualizar(downloadDieta);
	} 

	private DownloadDietaModel preencherDownloadDieta(DownloadDietaModel downloadDieta, Perfil perfil, StatusDieta statusDieta){
		Integer idDieta = Integer.parseInt(perfil.getIdDieta());

		downloadDieta.setImei(perfil.getIdPerfil());
		downloadDieta.setIdDieta(idDieta);
		downloadDieta.setDataUltimaAtualizacao(new Date());
		downloadDieta.setNomeUsuario(perfil.getNome());
		downloadDieta.setIdadeUsuario(perfil.getIdade());
		downloadDieta.setGeneroUsuario(getGenero(perfil));
		downloadDieta.setAlturaUsuario(perfil.getAltura());
		downloadDieta.setPesoUsuario(perfil.getPeso());
		downloadDieta.setEmailUsuario(perfil.getEmail());
		downloadDieta.setStatusDieta(statusDieta);
		return downloadDieta;
	}

	private StatusDieta getStatusDieta(String statusDietaStr) {
		if(StatusDieta.FAZENDO.name().equalsIgnoreCase(statusDietaStr.toUpperCase())){
			return StatusDieta.FAZENDO;

		}else if (StatusDieta.FEITA.name().equalsIgnoreCase(statusDietaStr.toUpperCase())){
			return StatusDieta.FEITA;

		}else if (StatusDieta.NAO_FEITA.name().equalsIgnoreCase(statusDietaStr.toUpperCase())){
			return StatusDieta.NAO_FEITA;
		}
		return null;
	}

	private Genero getGenero(Perfil perfil) {
		if(Genero.M.getDescricao().equalsIgnoreCase(perfil.getGenero())){
			return Genero.M;

		}else if(Genero.F.getDescricao().equalsIgnoreCase(perfil.getGenero())){

			return Genero.F;
		}
		return null;
	}
}
