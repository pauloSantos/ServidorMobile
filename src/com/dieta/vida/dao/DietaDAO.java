package com.dieta.vida.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.dieta.vida.model.DietaModel;
import com.site.dao.PrincipalDAO;


public class DietaDAO extends PrincipalDAO implements IDietaDAO{

	private static final String NOME_DIETA = "nomeDieta";
	
	private static final String ENCONTRAR_DIETA_POR_NOME_EXATO = 
			"select obj from " + DietaModel.class.getName() + " obj" +
					" where obj.nomeDieta =:" + NOME_DIETA;

	private static final String ENCONTRAR_DIETAS_DISPONIVEIS =
			"select obj from " + DietaModel.class.getName() + " obj";
	
	@Override
	public DietaModel encontrarDietaPorNomeExato(String nomeDieta) {
		Query query = getEntityManager().createQuery(ENCONTRAR_DIETA_POR_NOME_EXATO);
		query.setParameter(NOME_DIETA, nomeDieta);
		try {
			return (DietaModel) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DietaModel> encontrarTodasAsDietasDisponiveis() {
		Query query = getEntityManager().createQuery(ENCONTRAR_DIETAS_DISPONIVEIS);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<DietaModel>();
		}
	}
}
