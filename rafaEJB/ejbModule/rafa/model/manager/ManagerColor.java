package rafa.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import rafa.model.entities.Color;

/**
 * Session Bean implementation class ManagerColor
 */
@Stateless
@LocalBean
public class ManagerColor {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerColor() {
        
    }
    
    public List<Color>findAllColor(){
    	String consulta = "SELECT c FROM Color c";
    	Query q = em.createQuery(consulta, Color.class);
    	return q.getResultList();
    }
    
    public Color findColor(Integer color) {
    	return em.find(Color.class, color);
    }
    
    public void insertarColor(Color color){
    	Color nuevaForma = new Color();
    	nuevaForma.setNombre(color.getNombre());
    	em.persist(nuevaForma);
    }

    public void EliminarColor(Integer id ) {
    	Color color = findColor(id);
    	if(color!=null)
    		em.remove(color);
    }
    
    public void ActualizarColor(Color color) throws Exception{
    	Color c = findColor(color.getIdColor());
        if(c==null)
        	throw new Exception("No existe el color");
        c.setNombre(color.getNombre());
    	em.merge(c);
    }

}
