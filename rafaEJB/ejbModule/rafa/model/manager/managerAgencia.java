package rafa.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import rafa.model.entities.Agencia;


/**
 * Session Bean implementation class managerAgencia
 */
@Stateless
@LocalBean
public class managerAgencia {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public managerAgencia() {
        // TODO Auto-generated constructor stub
    }
    public List<Agencia>findAllAgencia(){
    	String consulta = "SELECT a FROM Agencia a";
    	Query q = em.createQuery(consulta, Agencia.class);
    	return q.getResultList();
    }
    
    public Agencia findAgencia(Integer agencia) {
    	return em.find(Agencia.class, agencia);
    }
    
    public void insertarAgencia(Agencia agencia){
    	Agencia nueva = new Agencia();
    	nueva.setNombre(agencia.getNombre());
    	nueva.setTelefono(agencia.getTelefono());
    	em.persist(nueva);
    }

    public void EliminarAgencia(Integer id ) {
    	Agencia agencia = findAgencia(id);
    	if(agencia!=null)
    		em.remove(agencia);
    }
    
    public void ActualizarAgencia(Agencia agencia) throws Exception{
    	Agencia a = findAgencia(agencia.getIdAgencia());
        if(a==null)
        	throw new Exception("No existe la agencia");
        a.setNombre(agencia.getNombre());
        a.setTelefono(agencia.getTelefono());
    	em.merge(a);
    }
}
