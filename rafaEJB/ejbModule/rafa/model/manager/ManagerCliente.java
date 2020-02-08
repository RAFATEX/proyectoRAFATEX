package rafa.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import rafa.model.entities.Cliente;

/**
 * Session Bean implementation class ManagerCliente
 */
@Stateless
@LocalBean
public class ManagerCliente {

    @PersistenceContext 
    EntityManager em;
	
    public ManagerCliente() {}
    
    public List<Cliente> FindAllCLientes()
    {
    	String consulta = "SELECT c FROM Cliente c";
    	Query q = em.createQuery(consulta, Cliente.class);
    	return q.getResultList();
    }
    
    public Cliente findClienteById(Integer id) {
    	return em.find(Cliente.class, id);
    }
    
    public void IngresarCLiente(Cliente cliente)
    {
    	Cliente nuevoCLiente = new Cliente();
    	
    	nuevoCLiente.setCedulaCliente(cliente.getCedulaCliente());
    	nuevoCLiente.setNombreCliente(cliente.getNombreCliente());
    	nuevoCLiente.setApellidoCliente(cliente.getApellidoCliente());
    	nuevoCLiente.setDireccionCliente(cliente.getDireccionCliente());
    	nuevoCLiente.setTelefono(cliente.getTelefono());
    	nuevoCLiente.setCelular(cliente.getCelular());
    	nuevoCLiente.setContrasenia(cliente.getContrasenia());
    	nuevoCLiente.setCorreoCliente(cliente.getCorreoCliente());
    	
    	em.persist(nuevoCLiente);
    }
    
    public void EliminarCliente(Integer id ) {
    	Cliente cliente = findClienteById(id);
    	if(cliente!=null)
    		em.remove(cliente);
    }
    
    public void ActualizarCliente(Cliente cliente) throws Exception{
    	Cliente CLiente = findClienteById(cliente.getIdCliente());
        if(CLiente==null)
        	throw new Exception("No existe el cliente");
        CLiente.setCedulaCliente(cliente.getCedulaCliente());
    	CLiente.setNombreCliente(cliente.getNombreCliente());
    	CLiente.setApellidoCliente(cliente.getApellidoCliente());
    	CLiente.setDireccionCliente(cliente.getDireccionCliente());
    	CLiente.setTelefono(cliente.getTelefono());
    	CLiente.setCelular(cliente.getCelular());
    	CLiente.setContrasenia(cliente.getContrasenia());
    	CLiente.setCorreoCliente(cliente.getCorreoCliente());
    	em.merge(CLiente);
    }
}
