package rafa.model.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import rafa.model.manager.ManagerCliente;
import rafa.model.manager.ManagerDAO;
import rafa.model.manager.ManagerProducto;
import rafa.model.entities.DetallePedido;
import rafa.model.entities.Pedido;
import rafa.model.entities.Producto;


/**
 * Session Bean implementation class ManagerDetallePedido
 */
@Stateless
@LocalBean
public class ManagerDetallePedido {

	@EJB
	private ManagerProducto managerProductos;
	@EJB
	private ManagerCliente managerClientes;
	@EJB
	private ManagerDAO managerDAO;
	@EJB 
	private ManagerProducto mProduto;
	
	private Producto producto;
	
    public ManagerDetallePedido() {
        // TODO Auto-generated constructor stub
    }

    
}
