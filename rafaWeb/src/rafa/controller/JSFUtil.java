package rafa.controller;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;


public class JSFUtil {

	public JSFUtil() {
		// TODO Auto-generated constructor stub
	}
	public static void crearMensaje(Severity severidad,String mensaje,String detalle){
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(severidad);
		msg.setSummary(mensaje);
		msg.setDetail(detalle);
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void crearMensajeERROR(String mensaje){
		crearMensaje(FacesMessage.SEVERITY_ERROR,mensaje,null);
	}
	
	public static void crearMensajeWARN(String mensaje){
		crearMensaje(FacesMessage.SEVERITY_WARN,mensaje,null);
	}
	
	public static void crearMensajeINFO(String mensaje){
		crearMensaje(FacesMessage.SEVERITY_INFO,mensaje,null);
	}

}
