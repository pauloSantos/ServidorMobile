package com.dieta.vida.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.dieta.vida.model.DownloadDietaModel;
import com.site.business.enums.StatusDieta;
import com.site.dao.PrincipalDAO;

public class DownloadDietaMobileDAO extends PrincipalDAO{

	private static final String STATUS = "status";
	private static final String IMEI = "imei";

	private static final String ENCONTRAR_DOWNLOADS_POR_IDENTIFICACAO_E_STATUS =
			"select obj from " + DownloadDietaModel.class.getName() + " obj" +
					" where obj.imei =:" + IMEI + 
					" and obj.statusDieta =:" + STATUS;


	public DownloadDietaModel encontrarDownloadPorIdentificacaoEStatus(Integer imei, StatusDieta status){
		Query query = getEntityManager().createQuery(ENCONTRAR_DOWNLOADS_POR_IDENTIFICACAO_E_STATUS);
		query.setParameter(IMEI, imei);
		query.setParameter(STATUS, status);
		try{
			DownloadDietaModel downloadDieta = (DownloadDietaModel) query.getSingleResult();
			return downloadDieta;
			
		}catch(NoResultException e){
			return null;
		}
	}
}
