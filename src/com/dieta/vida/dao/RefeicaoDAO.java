package com.dieta.vida.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.dieta.vida.model.RefeicaoModel;
import com.site.dao.PrincipalDAO;

public class RefeicaoDAO extends PrincipalDAO implements IRefeicaoDAO {

	private static final String ID_DIETA = "idDieta";
	private static final String ENCONTRAR_REFEICAO_POR_ID_DIETA =
			"select obj from " + RefeicaoModel.class.getName() + " obj" + 
			" where obj.dieta.id =:" + ID_DIETA;

	@Override
	@SuppressWarnings("unchecked")
	public List<RefeicaoModel> encontrarRefeicaoPorIdDieta(Long idDieta) {
		Query query = getEntityManager().createQuery(ENCONTRAR_REFEICAO_POR_ID_DIETA);
		query.setParameter(ID_DIETA, idDieta);
		try {
			List <RefeicaoModel>listaRefeicoes = query.getResultList();
			return listaRefeicoes;
		} catch (NoResultException e) {
			return new ArrayList<RefeicaoModel>();
		}finally{
			getEntityManager().getEntityManagerFactory().close();
		}
	}

}
