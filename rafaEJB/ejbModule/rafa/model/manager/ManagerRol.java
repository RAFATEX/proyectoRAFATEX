package rafa.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import rafa.model.entities.Rol;

/**
 * Session Bean implementation class managerRol
 */
@Stateless
@LocalBean
public class ManagerRol {
@PersistenceContext
private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerRol() {
        // TODO Auto-generated constructor stub
    }

	public List<Rol> findAllRol(){
    	String consulta="SELECT r FROM Rol r";
    	Query q = em.createQuery(consulta, Rol.class);
    	return q.getResultList();
    }

}