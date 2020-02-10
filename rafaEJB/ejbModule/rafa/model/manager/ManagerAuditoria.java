package rafa.model.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import rafa.model.manager.ManagerDAO;

/**
 * Session Bean implementation class ManagerAuditoria
 */
@Stateless
@LocalBean
public class ManagerAuditoria {
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerSeguridad managerSeguridad;
	
    public ManagerAuditoria() {
        // TODO Auto-generated constructor stub
    }

}
