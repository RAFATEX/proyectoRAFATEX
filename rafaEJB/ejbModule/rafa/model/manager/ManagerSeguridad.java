package rafa.model.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import rafa.model.dto.LoginDTO;
import rafa.model.entities.Cliente;
import rafa.model.manager.ManagerCliente;
import rafa.model.manager.ManagerDAO;
import rafa.model.manager.ManagerEmpleado;

/**
 * Session Bean implementation class ManagerSeguridad
 */
@Stateless
@LocalBean
public class ManagerSeguridad {
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerCliente managerClientes;
	@EJB 
	private ManagerEmpleado managerEmpleado;
	
	
    public ManagerSeguridad() {
        // TODO Auto-generated constructor stub
    }

    public LoginDTO accederSistemaCliente(String cedula, String contrasenia) throws Exception{
    	Cliente cliente = (Cliente)managerDAO.findById(Cliente.class, cedula);
    	if (cliente ==null) {
			return null;
		}
    	if (!cliente.getContrasenia().equals(contrasenia)) {
    		throw new Exception("Error en cedula y/o clave");
    	}
    	LoginDTO loginDTO = new LoginDTO();
    	
    	loginDTO.setCedulaCliente(cliente.getCedulaCliente());
    	loginDTO.setClave(cliente.getContrasenia());
    	return loginDTO;
    }
}
