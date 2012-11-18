package com.dieta.vida.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.dieta.vida.model.DietaModel;
import com.site.dao.PrincipalDAO;


public class DietaDAO extends PrincipalDAO implements IDietaDAO{

	private static final String ID_DIETA = "idDieta";

	private static final String ENCONTRAR_DIETAS_DISPONIVEIS =
			"select obj from " + DietaModel.class.getName() + " obj";
	
	private static final String ENCONTRAR_DIETA_POR_ID =
			"select obj from " + DietaModel.class.getName() + " obj" +
			" where obj.id =:" + ID_DIETA;

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
	
	@Override
	public DietaModel encontrarDietaPorId(Long id) {
		Query query = getEntityManager().createQuery(ENCONTRAR_DIETA_POR_ID);
		query.setParameter(ID_DIETA, id);
		try {
			return (DietaModel) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
