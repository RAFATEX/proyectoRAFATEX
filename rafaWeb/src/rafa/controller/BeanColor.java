package rafa.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import rafa.model.entities.Color;
import rafa.model.manager.ManagerColor;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanColor implements Serializable {


	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerColor managerColor;
	private List<Color> ListaColores;
	private Color color;
	private boolean panelColapsado;
	private Color colorSeleccionado;
	private int idcolor;
	
	@PostConstruct
	public void inicializar() {
		ListaColores=managerColor.findAllColor();
		color=new Color();
		panelColapsado = true;
	}
	
	public void actionInsertarColor() {
		try {
			managerColor.insertarColor(color);
			ListaColores=managerColor.findAllColor();
			color=new Color();
			JSFUtil.crearMensajeINFO("Color Ingresado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionEliminarColor(Integer idColor) {
		managerColor.EliminarColor(idColor);
		ListaColores=managerColor.findAllColor();
		JSFUtil.crearMensajeINFO("Color Eliminado");
	}
	public void actionListenerSeleccionarColor(Color color) {
		colorSeleccionado=color;
	}
	public void actionListenerActualizarColor() {
		try {
			managerColor.ActualizarColor(colorSeleccionado);
			ListaColores=managerColor.findAllColor();
			JSFUtil.crearMensajeINFO("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerColapsadoPanel() {
		panelColapsado=!panelColapsado;
	}
	public List<Color> getListaColores() {
		return ListaColores;
	}
	
	public Color getColor() {
		return color;
	}
	

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColorSeleccionado() {
		return colorSeleccionado;
	}

	public void setColorSeleccionado(Color colorSeleccionado) {
		this.colorSeleccionado = colorSeleccionado;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public int getIdcolor() {
		return idcolor;
	}

	public void setIdcolor(int idcolor) {
		this.idcolor = idcolor;
	}

	public void setListaColores(List<Color> listaColores) {
		ListaColores = listaColores;
	}

	
}
