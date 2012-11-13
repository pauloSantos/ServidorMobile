package com.dieta.vida.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.dieta.vida.dao.AlimentoDAO;
import com.dieta.vida.dao.DietaDAO;
import com.dieta.vida.dao.IAlimentoDAO;
import com.dieta.vida.dao.IDietaDAO;
import com.dieta.vida.dao.IRefeicaoDAO;
import com.dieta.vida.dao.RefeicaoDAO;
import com.dieta.vida.model.AlimentoModel;
import com.dieta.vida.model.DietaModel;
import com.dieta.vida.model.RefeicaoModel;
import com.dieta.vida.xml.AlimentoXML;
import com.dieta.vida.xml.DietaXML;
import com.dieta.vida.xml.DietasDisponiveisXML;
import com.dieta.vida.xml.RefeicaoXML;

@Path("/mobile")
public class MobileResource {


	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String respondAsReady(){
		return "Web Service esta no ar";
	}

	@GET
	@Path("/download/{nomeDieta}")
	@Produces(MediaType.APPLICATION_XML)
	public DietaXML getDietaEscolhida(@PathParam("nomeDieta") String nomeDieta){
		DietaXML dietaXML = new DietaXML();
		IDietaDAO dietaDAO = new DietaDAO();
		DietaModel dieta = dietaDAO.encontrarDietaPorNomeExato(nomeDieta);
		
		if(dieta != null){
			List<RefeicaoXML> listaRefeicaoXML = criarRefeicaoXML(dieta);
			dietaXML.setNomeDieta(dieta.getNomeDieta());
			dietaXML.setDuracao(dieta.getPeriodoDieta());
			dietaXML.setRefeicoes(listaRefeicaoXML);
		}
		return dietaXML;
	}

	@GET
	@Path("/dietas/disponiveis")
	public DietasDisponiveisXML getDietasDisponiveis(){
		IDietaDAO dietaDAO = new DietaDAO();
		List<DietaModel> listaDietasDisponiveis = dietaDAO.encontrarTodasAsDietasDisponiveis();
		List<DietaXML> listaDietaXML = new ArrayList<DietaXML>();
		
		for (DietaModel dieta : listaDietasDisponiveis) {
			DietaXML dietaXML = new DietaXML();
			dietaXML.setId(dieta.getId());
			dietaXML.setNomeDieta(dieta.getNomeDieta());
			listaDietaXML.add(dietaXML);
		}
		DietasDisponiveisXML dietasDisponiveisXML = new DietasDisponiveisXML();
		dietasDisponiveisXML.setListaDietasXML(listaDietaXML);
		return dietasDisponiveisXML;
	}
	
	private List<RefeicaoXML> criarRefeicaoXML(DietaModel dieta) {
		IRefeicaoDAO refeicaoDAO = new RefeicaoDAO();
		List<RefeicaoModel> listaRefeicoes = refeicaoDAO.encontrarRefeicaoPorIdDieta(dieta.getId());
		List<RefeicaoXML> listaRefeicaoXML = new ArrayList<RefeicaoXML>();
		for (RefeicaoModel refeicao : listaRefeicoes) {
			RefeicaoXML refeicaoXML = new RefeicaoXML();
			List<AlimentoXML> listaAlimentoXML = criarListaAlimentoXML(refeicao);
			refeicaoXML.setAlimento(listaAlimentoXML);
			refeicaoXML.setTipoRefeicao(refeicao.getOpcaoRefeicao().toString());
			refeicaoXML.setHorario(refeicao.getHorarioRefeicao());
			listaRefeicaoXML.add(refeicaoXML);
		}
		return listaRefeicaoXML;
	}

	private List<AlimentoXML> criarListaAlimentoXML(RefeicaoModel refeicao) {
		IAlimentoDAO alimentoDAO = new AlimentoDAO();
		List<AlimentoModel> listaAlimentos = alimentoDAO.encontrarAlimentoPorIdRefeicao(refeicao.getId());
		List<AlimentoXML> listaAlimentoXML = new ArrayList<AlimentoXML>();
		for (AlimentoModel alimento : listaAlimentos) {
			AlimentoXML alimentoXML = new AlimentoXML();
			alimentoXML.setNomeAlimento(alimento.getNomeAlimento());
			alimentoXML.setQuantidade(alimento.getQuantidadeAlimento());
			alimentoXML.setUnidadeDeMedida(alimento.getUnidadeDeMedida());
			listaAlimentoXML.add(alimentoXML);
		}
		return listaAlimentoXML;
	}

	public static void main(String[] args) {
		IAlimentoDAO alimentoDAO = new AlimentoDAO();
		AlimentoModel alimento = alimentoDAO.encontrarAlimentoPorId(1l);
		System.out.println(alimento.getNomeAlimento());
	}
}
