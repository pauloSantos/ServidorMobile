package com.dieta.vida.business;

public interface IDownloadDietaMobileBusiness {

	void criarDownloadDieta(String perfilJson);
	void atualizarDownloadDieta(String perfilJson, String statusDietaStr);
}
