package rafa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import rafa.model.entities.FormaPago;
import rafa.model.entities.Parametro;
import rafa.model.manager.ManagerFormaPago;

@Named
@SessionScoped
public class BeanFormaPago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerFormaPago mFormaPago;
	private FormaPago formapago;
	private Parametro parametros;
	private List<FormaPago> listaFormaPago;
	private List<Parametro> listaParametro;
	private FormaPago formapagoSeleccionada;
	//private boolean panelColapsado;

	public BeanFormaPago() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializar() {
		formapago = new FormaPago();
		listaFormaPago = mFormaPago.findAllFormaPago();
		listaParametro = mFormaPago.findAllParametro();
		//panelColapsado = true;
	}
	
	/*public void actionListenerPanelColapsado() {
		panelColapsado=!panelColapsado;
	}*/
	public void actionIngresarParametro() {
		try {
			mFormaPago.insertarParametro(parametros);
			listaParametro = mFormaPago.findAllParametro();
			parametros = new Parametro();
			JSFUtil.crearMensajeINFO("Ingresado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionIngresarFormaP() {
		try {
			mFormaPago.insertarFormaPago(formapago);
			listaFormaPago = mFormaPago.findAllFormaPago();
			formapago = new FormaPago();
			JSFUtil.crearMensajeINFO("Ingresado correctamente");
			
		}catch(Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionEliminarCliente(Integer id) {
		mFormaPago.EliminarFormaPago(id);
		listaFormaPago = mFormaPago.findAllFormaPago();
		JSFUtil.crearMensajeINFO("Forma de Pago eliminada");
	}
	
	public void actionSeleccionarFP(FormaPago formapago) {
		formapagoSeleccionada=formapago;
	}
	
	public void actionActualizarFP() {
		try {
			mFormaPago.ActualizarCliente(formapagoSeleccionada);
			listaFormaPago=mFormaPago.findAllFormaPago();
			JSFUtil.crearMensajeINFO("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public FormaPago getFormapago() {
		return formapago;
	}

	public void setFormapago(FormaPago formapago) {
		this.formapago = formapago;
	}

	public List<FormaPago> getListaFormaPago() {
		return listaFormaPago;
	}

	public void setListaFormaPago(List<FormaPago> listaFormaPago) {
		this.listaFormaPago = listaFormaPago;
	}

	public FormaPago getFormapagoSeleccionada() {
		return formapagoSeleccionada;
	}

	public void setFormapagoSeleccionada(FormaPago formapagoSeleccionada) {
		this.formapagoSeleccionada = formapagoSeleccionada;
	}

	public ManagerFormaPago getmFormaPago() {
		return mFormaPago;
	}

	public void setmFormaPago(ManagerFormaPago mFormaPago) {
		this.mFormaPago = mFormaPago;
	}

	public Parametro getParametros() {
		return parametros;
	}

	public void setParametros(Parametro parametros) {
		this.parametros = parametros;
	}

	public List<Parametro> getListaParametro() {
		return listaParametro;
	}

	public void setListaParametro(List<Parametro> listaParametro) {
		this.listaParametro = listaParametro;
	}

	/*public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}*/
	
}
