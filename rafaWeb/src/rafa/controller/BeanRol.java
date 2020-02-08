package rafa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import rafa.model.entities.Rol;
import rafa.model.manager.ManagerRol;

@Named
@SessionScoped
public class BeanRol implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerRol managerrol;
	private List<Rol> listarol;
	private Rol rol;

	
	@PostConstruct
	public void inicializar() {
		listarol=managerrol.findAllRol();
		rol = new Rol();
	}

	public List<Rol> getListarol() {
		return listarol;
	}

	public void setListarol(List<Rol> listarol) {
		this.listarol = listarol;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
