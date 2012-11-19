package com.dieta.vida.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.dieta.vida.dao.AlimentoDAO;
import com.dieta.vida.dao.DietaDAO;
import com.dieta.vida.dao.DownloadDietaMobileDAO;
import com.dieta.vida.dao.IAlimentoDAO;
import com.dieta.vida.dao.IDietaDAO;
import com.dieta.vida.dao.IRefeicaoDAO;
import com.dieta.vida.dao.RefeicaoDAO;
import com.dieta.vida.model.AlimentoModel;
import com.dieta.vida.model.DietaModel;
import com.dieta.vida.model.DownloadDietaModel;
import com.dieta.vida.model.Perfil;
import com.dieta.vida.model.RefeicaoModel;
import com.dieta.vida.xml.AlimentoXML;
import com.dieta.vida.xml.DietaXML;
import com.dieta.vida.xml.DietasDisponiveisXML;
import com.dieta.vida.xml.RefeicaoXML;
import com.google.gson.Gson;
import com.site.business.enums.Genero;
import com.site.business.enums.StatusDieta;

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
	@Path("/download/{idDieta}")
	@Produces(MediaType.APPLICATION_XML)
	public DietaXML getDietaEscolhida(@PathParam("idDieta") String idDietaStr){
		DietaXML dietaXML = new DietaXML();
		IDietaDAO dietaDAO = new DietaDAO();
		Long idDieta = Long.parseLong(idDietaStr);

		DietaModel dieta = dietaDAO.encontrarDietaPorId(idDieta);

		if(dieta != null){
			List<RefeicaoXML> listaRefeicaoXML = criarRefeicaoXML(dieta);
			dietaXML.setIdentificacaoDieta(dieta.getId());
			dietaXML.setNomeDieta(dieta.getNomeDieta());
			dietaXML.setDuracao(dieta.getPeriodoDieta());
			dietaXML.setRefeicoes(listaRefeicaoXML);
		}
		return dietaXML;
	}

	@POST
	@Path("/perfil/criacao")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response gravarPerfil(String perfilJson){
		System.out.println("====================================");
		System.out.println("RECURSO: /resource/mobile/perfil");
		System.out.println("====================================");

		
		
		criarDownloadDieta(perfilJson);

		return Response.ok().build();
	}

	private void criarDownloadDieta(String perfilJson) {
		DownloadDietaMobileDAO downloadDietaDAO = new DownloadDietaMobileDAO();
		Gson gson = new Gson();
		Perfil perfil = gson.fromJson(perfilJson, Perfil.class);
		
		DownloadDietaModel downloadDietaAntigo = downloadDietaDAO.encontrarDownloadPorIdentificacaoEStatus(perfil.getIdPerfil(), StatusDieta.FAZENDO);
		
		if(downloadDietaAntigo != null){
			downloadDietaAntigo.setStatusDieta(StatusDieta.NAO_FEITA);
			downloadDietaDAO.atualizar(downloadDietaAntigo);
		}
		
		DownloadDietaModel downloadDieta = new DownloadDietaModel();
		downloadDieta.setImei(perfil.getIdPerfil());
		downloadDieta.setDataSolicitacao(new Date());
		downloadDieta.setDataUltimaAtualizacao(new Date());
		downloadDieta.setNomeUsuario(perfil.getNome());
		downloadDieta.setIdadeUsuario(perfil.getIdade());
		downloadDieta.setGeneroUsuario(getGenero(perfil));
		downloadDieta.setAlturaUsuario(perfil.getAltura());
		downloadDieta.setPesoUsuario(perfil.getPeso());
		downloadDieta.setEmailUsuario(perfil.getEmail());
		downloadDieta.setStatusDieta(StatusDieta.FAZENDO);

		downloadDietaDAO.inserir(downloadDieta);

	}

	private Genero getGenero(Perfil perfil) {
		if(Genero.M.getDescricao().equalsIgnoreCase(perfil.getGenero())){
			return Genero.M;

		}else if(Genero.F.getDescricao().equalsIgnoreCase(perfil.getGenero())){

			return Genero.F;
		}
		return null;
	} 

	@GET
	@Path("/dietas/disponiveis")
	public DietasDisponiveisXML getDietasDisponiveis(){
		System.out.println("Voce esta no recurso /resource/mobile/dietas/disponiveis");
		IDietaDAO dietaDAO = new DietaDAO();
		List<DietaModel> listaDietasDisponiveis = dietaDAO.encontrarTodasAsDietasDisponiveis();
		List<DietaXML> listaDietaXML = new ArrayList<DietaXML>();

		for (DietaModel dieta : listaDietasDisponiveis) {
			DietaXML dietaXML = new DietaXML();
			dietaXML.setIdentificacaoDieta(dieta.getId());
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
}
