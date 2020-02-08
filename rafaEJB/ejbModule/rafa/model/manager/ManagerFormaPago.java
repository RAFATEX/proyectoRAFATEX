package rafa.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import rafa.model.entities.FormaPago;



/**
 * Session Bean implementation class ManagerFormaPago
 */
@Stateless
@LocalBean
public class ManagerFormaPago {

	@PersistenceContext 
    private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerFormaPago() {
        // TODO Auto-generated constructor stub
    }
    
    public List<FormaPago>findAllFormaPago(){
    	String consulta = "SELECT f FROM FormaPago f";
    	Query q = em.createQuery(consulta, FormaPago.class);
    	return q.getResultList();
    }
    
    public FormaPago findFormaPago(Integer formapago) {
    	return em.find(FormaPago.class, formapago);
    }
    
    public void insertarFormaPago(FormaPago formapago){
    	FormaPago nuevaForma = new FormaPago();
    	nuevaForma.setTipo(formapago.getTipo());
    	em.persist(nuevaForma);
    }

    public void EliminarFormaPago(Integer id ) {
    	FormaPago formapago = findFormaPago(id);
    	if(formapago!=null)
    		em.remove(formapago);
    }
    
    public void ActualizarCliente(FormaPago formap) throws Exception{
    	FormaPago fp = findFormaPago(formap.getIdFormaPago());
        if(fp==null)
        	throw new Exception("No existe la forma de pago");
        fp.setTipo(formap.getTipo());
    	em.merge(fp);
    }
}
