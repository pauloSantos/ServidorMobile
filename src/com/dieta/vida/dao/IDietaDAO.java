package com.dieta.vida.dao;

import java.util.List;

import com.dieta.vida.model.DietaModel;

public interface IDietaDAO {

	DietaModel encontrarDietaPorNomeExato(String nomeDieta);
	List<DietaModel> encontrarTodasAsDietasDisponiveis();
	
}
