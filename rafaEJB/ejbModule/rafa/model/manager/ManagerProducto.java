package rafa.model.manager;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import rafa.model.manager.ManagerDAO;

import rafa.model.entities.Producto;
import rafa.model.entities.Color;
import rafa.model.entities.Empleado;

/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerProducto {

	@EJB
	private ManagerDAO managerDAO;
	private EntityManager em;
	
    public ManagerProducto() {
        // TODO Auto-generated constructor stub
    }
  	public int obtenerExistencia(Integer codigoProducto) throws Exception{
  		Producto p;
  		p=findProductoById(codigoProducto);
  		return p.getCantidadExistente().intValue();
  	}
 
  	@SuppressWarnings("unchecked")
  	public List<Producto> findAllProductos(){
  		return managerDAO.findAll(Producto.class, "o.nombre");
  	}
  	
 
  	public Producto findProductoById(Number codigoProducto) throws Exception{
  		return  em.find(Producto.class, codigoProducto);
  	}
 
   
    public void insertarProducto(Producto producto, Number idcolor,Number idempleado) {
    	Color color = (Color) em.find(Color.class, idcolor);    
    	Empleado empleado=(Empleado)em.find(Empleado.class, idempleado);
    	producto.setColor(color);
    	//producto.setEmpleado(empleado);
    	em.persist(producto);
    }
 
  	public void eliminarProducto(Integer codigoProducto) throws Exception{
  		managerDAO.eliminar(Producto.class, codigoProducto);
  	}
 
  	public void actualizarProducto(Producto producto) throws Exception{
  		Producto p=null;
  		try {
  			//buscamos el producto a modificar desde la bdd:
  			p=findProductoById(producto.getIdProducto());
  			//actualizamos las propiedades:
  			p.setDescripcion(producto.getDescripcion());
  			p.setCantidadExistente(producto.getCantidadExistente());
  			p.setNombre(producto.getNombre());
  			p.setPrecioUnitario(producto.getPrecioUnitario());
  			p.setPrecioPorMayor(producto.getPrecioPorMayor());
  			p.setRutaImagen(producto.getRutaImagen());
  			p.setTamanio(producto.getTamanio());
  			p.setColor(producto.getColor());
  			p.setCantidadMinima(producto.getCantidadMinima());
  			//p.setEmpleado(producto.getEmpleado());
  			//actualizamos:
  			managerDAO.actualizar(p);
  		} catch (Exception e) {
  			e.printStackTrace();
  			throw new Exception(e.getMessage());
  		}
  	}

}
