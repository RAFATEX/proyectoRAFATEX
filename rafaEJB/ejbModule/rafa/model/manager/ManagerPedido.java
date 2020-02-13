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

	public List<Pedido> findAllPedido() {
		String consulta = "SELECT e FROM Pedido e";
		Query q = em.createQuery(consulta, Pedido.class);
		return q.getResultList();
	}

	public Pedido findPedido(Integer idPedido) {
		return em.find(Pedido.class, idPedido);
	}

	public void insertarPedido(Pedido p, Cliente cliente, Agencia agencia) {
		Pedido nuevo = new Pedido();
		
		nuevo.setFechaPedido(p.getFechaPedido());
		nuevo.setFechaEntregaPedido(p.getFechaEntregaPedido());
		nuevo.setCliente(cliente);
		nuevo.setPaisEntrega(p.getPaisEntrega());
		nuevo.setCiudadEntrega(p.getCiudadEntrega());
		nuevo.setDireccionEntrega(p.getDireccionEntrega());
		nuevo.setDescripcionEstado(p.getDescripcionEstado());
		nuevo.setAgencia(agencia);
			
		em.persist(p);
	}

	public void eliminarPedido(Integer id ) throws Exception{
		Pedido p = findPedido(id);
		if(p!=null)
			throw new Exception("No existe");
		em.remove(p);
	}
}
