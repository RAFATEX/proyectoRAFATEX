package rafa.model.manager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ManagerParametros
 */
@Stateless
@LocalBean
public class ManagerParametros {
	@PersistenceContext 
    EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerParametros() {
        // TODO Auto-generated constructor stub
    }

}
