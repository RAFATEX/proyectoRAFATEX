package rafa.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import rafa.model.entities.Empleado;
import rafa.model.entities.Rol;

/**
 * Session Bean implementation class ManagerEmpleado
 */
@Stateless
@LocalBean
public class ManagerEmpleado {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerEmpleado() {

	}

	public List<Empleado> findAllEmpleados() {
		String consulta = "SELECT e FROM Empleado e";
		Query q = em.createQuery(consulta, Empleado.class);
		return q.getResultList();
	}

	public Empleado findEmpleado(Integer idempleado) {
		return em.find(Empleado.class, idempleado);
	}

	public void insertarEmpleado(Empleado empleado, Integer idRol) {
		Rol rol = (Rol) em.find(Rol.class, idRol);
		empleado.setRol(rol);
		em.persist(empleado);

		/*
		 * Empleado nuevaForma = new Empleado();
		 * 
		 * nuevaForma.setCedula(empleado.getCedula());
		 * nuevaForma.setNombre(empleado.getNombre());
		 * nuevaForma.setApellido(empleado.getApellido());
		 * nuevaForma.setCorreo(empleado.getCorreo());
		 * nuevaForma.setDireccion(empleado.getDireccion());
		 * nuevaForma.setTelefono(empleado.getTelefono());
		 * nuevaForma.setCelular(empleado.getCelular());
		 * //e.setRol(empleado.getRol().getNombreRol());
		 * nuevaForma.setRol(empleado.getRol());
		 * nuevaForma.setContrasenia(empleado.getContrasenia()); em.persist(nuevaForma);
		 * 
		 * if(findEmpleado(empleado.getIdEmpleado())!=null) throw new
		 * Exception("Este Empleado ya existe"); //Rol rol =(Rol) em.find(Rol.class,
		 * idrol); //empleado.setRol(rol); em.persist(empleado);
		 */
	}

	public void actualizarEmpleado(Empleado empleado) throws Exception {
		Empleado e = findEmpleado(empleado.getIdEmpleado());
		if (e == null)
			throw new Exception("No existe el Empleado Indicado");
		e.setCedula(empleado.getCedula());
		e.setNombre(empleado.getNombre());
		e.setApellido(empleado.getApellido());
		e.setCorreo(empleado.getCorreo());
		e.setDireccion(empleado.getDireccion());
		e.setTelefono(empleado.getTelefono());
		e.setCelular(empleado.getCelular());
		// e.setRol(empleado.getRol().getNombreRol());
		e.setRol(empleado.getRol());
		e.setContrasenia(empleado.getContrasenia());
		em.merge(e);

	}

	public void eliminarEmpleado(Integer idEmpleado) {
		Empleado e = findEmpleado(idEmpleado);
		if (e != null)
			em.remove(e);
	}
}
