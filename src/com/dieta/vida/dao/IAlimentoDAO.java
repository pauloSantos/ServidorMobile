package com.dieta.vida.dao;

import java.util.List;

import com.dieta.vida.model.AlimentoModel;


public interface IAlimentoDAO {

	AlimentoModel encontrarAlimentoPorId(Long id);
	List<AlimentoModel> encontrarAlimentoPorIdRefeicao(Long idRefeicao);
	
}
