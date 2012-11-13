package com.dieta.vida.dao;

import java.util.List;

import com.dieta.vida.model.RefeicaoModel;


public interface IRefeicaoDAO {

	RefeicaoModel encontrarRefeicaoPorId(Long id);
	List<RefeicaoModel> encontrarRefeicaoPorIdDieta(Long idDieta);
	
}
