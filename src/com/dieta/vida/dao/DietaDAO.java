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
			List<DietaModel> dietasDisponiveis = query.getResultList();
			return dietasDisponiveis;
		} catch (NoResultException e) {
			return new ArrayList<DietaModel>();
		}finally{
			getEntityManager().getEntityManagerFactory().close();
		}
	}
	
	@Override
	public DietaModel encontrarDietaPorId(Long id) {
		Query query = getEntityManager().createQuery(ENCONTRAR_DIETA_POR_ID);
		query.setParameter(ID_DIETA, id);
		try {
			 DietaModel dieta = (DietaModel) query.getSingleResult();
			return dieta;
		} catch (NoResultException e) {
			return null;
		}finally{
			getEntityManager().getEntityManagerFactory().close();
		}
	}
}
