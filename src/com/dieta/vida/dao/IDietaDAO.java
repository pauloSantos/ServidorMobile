package com.dieta.vida.dao;

import java.util.List;

import com.dieta.vida.model.DietaModel;

public interface IDietaDAO {

	List<DietaModel> encontrarTodasAsDietasDisponiveis();
	DietaModel encontrarDietaPorId(Long id);
	
}
