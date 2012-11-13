package com.dieta.vida.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.dieta.vida.model.AlimentoModel;
import com.site.dao.PrincipalDAO;

public class AlimentoDAO extends PrincipalDAO implements IAlimentoDAO {

	private static final String ID_REFEICAO = "idRefeicao";
	
	private static final String ENCONTRAR_ALIMENTO_POR_ID_REFEICAO =
			"select obj from " + AlimentoModel.class.getName() + " obj" +
			" where obj.refeicao.id =:" + ID_REFEICAO	;

	@Override
	public AlimentoModel encontrarAlimentoPorId(Long id) {
		try{
			return getEntityManager().getReference(AlimentoModel.class, id);
		}catch(EntityNotFoundException e ){
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AlimentoModel> encontrarAlimentoPorIdRefeicao(Long idRefeicao) {
		Query query = getEntityManager().createQuery(ENCONTRAR_ALIMENTO_POR_ID_REFEICAO);
		query.setParameter(ID_REFEICAO, idRefeicao);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<AlimentoModel>();
		}
	}

}
