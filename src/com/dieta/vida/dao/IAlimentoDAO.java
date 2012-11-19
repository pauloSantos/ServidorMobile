package com.dieta.vida.dao;

import java.util.List;

import com.dieta.vida.model.AlimentoModel;


public interface IAlimentoDAO{

	List<AlimentoModel> encontrarAlimentoPorIdRefeicao(Long idRefeicao);
}
