package rafa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import rafa.model.entities.Producto;
import rafa.model.manager.ManagerProducto;

@Named
@SessionScoped
public class BeanCarrito implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerProducto mCarrito;
	
	private List<Producto>carrito;
	private List<Producto>listaProducto;
	
	private Producto nuevocarrito;
	

	public BeanCarrito() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		nuevocarrito = new Producto();
		listaProducto = mCarrito.findAllProductos();
	}
	
	public void actionAgregarCarrito(Producto p) {
		carrito = mCarrito.AgregarProducto(carrito, p);
	}
	
	public void actionEliminarCarrito(Producto p) {
		carrito = mCarrito.EliminarCarrito(carrito, p.getIdProducto());
	}

	public List<Producto> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<Producto> carrito) {
		this.carrito = carrito;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public Producto getNuevocarrito() {
		return nuevocarrito;
	}

	public void setNuevocarrito(Producto nuevocarrito) {
		this.nuevocarrito = nuevocarrito;
	}
	
	
}
