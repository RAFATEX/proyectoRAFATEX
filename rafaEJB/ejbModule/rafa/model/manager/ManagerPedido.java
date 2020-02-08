package rafa.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import rafa.model.entities.Agencia;
import rafa.model.entities.Cliente;
import rafa.model.entities.Empleado;
import rafa.model.entities.Pedido;

/**
 * Session Bean implementation class ManagerEmpleado
 */
@Stateless
@LocalBean
public class ManagerPedido {
	@PersistenceContext
	private EntityManager em;	
    /**	
     * Default constructor. 
     */
    public ManagerPedido() {
        
    }
    public List<Pedido> findAllPedido(){
    	String consulta="SELECT e FROM Pedido e";
    	Query q=em.createQuery(consulta,Pedido.class);
    	return q.getResultList();
    }
    public Empleado findPedido(Integer idPedido) {
    	return em.find(Empleado.class,idPedido);
    }
 public void insertarPedido(Pedido empleado, Integer idCliente, Integer idAgencia){
    Cliente cliente=(Cliente) em.find(Cliente.class, idCliente);
    Agencia agencia =(Agencia) em.find(Agencia.class, idAgencia);
    empleado.setCliente(cliente);
    empleado.setAgencia(agencia);
    
    em.persist(empleado);
	 
	 /*Empleado nuevaForma = new Empleado();
 	
 	 nuevaForma.setCedula(empleado.getCedula());
	 nuevaForma.setNombre(empleado.getNombre());
	 nuevaForma.setApellido(empleado.getApellido());
	nuevaForma.setCorreo(empleado.getCorreo());
	nuevaForma.setDireccion(empleado.getDireccion());
	nuevaForma.setTelefono(empleado.getTelefono());
	nuevaForma.setCelular(empleado.getCelular());
	//e.setRol(empleado.getRol().getNombreRol());
	nuevaForma.setRol(empleado.getRol());
	nuevaForma.setContrasenia(empleado.getContrasenia());
 	em.persist(nuevaForma);
 
    	if(findEmpleado(empleado.getIdEmpleado())!=null)
       		throw new Exception("Este Empleado ya existe");
    		//Rol rol =(Rol) em.find(Rol.class, idrol);
    		//empleado.setRol(rol);
    				em.persist(empleado);*/
    }
   

}
