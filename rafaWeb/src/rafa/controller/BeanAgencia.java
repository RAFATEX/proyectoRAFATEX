package rafa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import rafa.model.entities.Agencia;

import rafa.model.manager.managerAgencia;

@Named
@SessionScoped
public class BeanAgencia implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private managerAgencia manageragencia;
	private List<Agencia> listaAgencia;
	private Agencia agencia;
	private boolean panelColapsado;
	private Agencia agenciaSeleccionada;

	@PostConstruct
	public void inicializar() {
		listaAgencia = manageragencia.findAllAgencia();
		agencia = new Agencia();
		panelColapsado = true;
	}

	public void actionInsertarAgencia() {
		try {
			manageragencia.insertarAgencia(agencia);
			listaAgencia = manageragencia.findAllAgencia();
			agencia = new Agencia();
			JSFUtil.crearMensajeINFO("Agencia Insertada");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionEliminarAgencia(Integer idagencia) {

		manageragencia.EliminarAgencia(idagencia);
		listaAgencia = manageragencia.findAllAgencia();
		JSFUtil.crearMensajeINFO("Agencia ELiminada");

	}

	public void actionSeleccionarAgencia(Agencia agencia) {
		agenciaSeleccionada = agencia;
	}

	public void actionActualizarAgencia() {
		try {
			manageragencia.ActualizarAgencia(agenciaSeleccionada);
			listaAgencia = manageragencia.findAllAgencia();
			JSFUtil.crearMensajeINFO("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();

		}
	}

	public void actionListenerColapsadoPanel() {
		panelColapsado = !panelColapsado;
	}

	public List<Agencia> getListaAgencia() {
		return listaAgencia;
	}

	public void setListaAgencia(List<Agencia> listaAgencia) {
		this.listaAgencia = listaAgencia;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Agencia getAgenciaSeleccionada() {
		return agenciaSeleccionada;
	}

	public void setAgenciaSeleccionada(Agencia agenciaSeleccionada) {
		this.agenciaSeleccionada = agenciaSeleccionada;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	
}
