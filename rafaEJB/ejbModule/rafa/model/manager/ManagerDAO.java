package rafa.model.manager;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import rafa.model.util.ModelUtil;

@Stateless
@LocalBean
public class ManagerDAO {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerDAO() {
        
    }
    
 
	@SuppressWarnings("rawtypes")
	public void mostrarLog(Class clase, String nombreMetodo, String mensaje) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(format.format(new Date())+ " [" + clase.getSimpleName() + "/" + nombreMetodo + "]: " + mensaje);
	}

	@SuppressWarnings("rawtypes")
	public List findAll(Class clase, String orderBy) {
		mostrarLog(this.getClass(), "findAll", clase.getSimpleName()+" orderBy " + orderBy);
		Query q;
		List listado;
		String sentenciaJPQL;
		if(ModelUtil.isEmpty(orderBy))
			sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o";
		else
			sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o ORDER BY " + orderBy;
		q = em.createQuery(sentenciaJPQL);
		listado = q.getResultList();
		return listado;
	}

	/**
	 * finder Generico que devuelve todas las entidades de una tabla.
	 * 
	 * @param clase  La clase que se desea consultar. Por ejemplo:
	 *        <ul>
	 *        	<li>Usuario.class</li>
	 *        </ul>
	 * @return Listado resultante.
	 */
	@SuppressWarnings("rawtypes")
	public List findAll(Class clase) {
		return findAll(clase, null);
	}
	
	/**
	 * Finder generico para buscar un objeto especifico.
	 * 
	 * @param clase La clase sobre la que se desea consultar, ejemplo: Usuario.class
	 * @param pID Identificador (la clave primaria) que permitira la busqueda.
	 * @return El objeto solicitado (si existiera).
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findById(Class clase, Object pID) throws Exception {
		mostrarLog(this.getClass(),"findById", clase.getSimpleName() + " : " + pID);
		if (pID == null)
			throw new Exception("Debe especificar el codigo para buscar el dato.");
		Object o;
		try {
			o = em.find(clase, pID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar la informacion especificada (" + pID + ") : " + e.getMessage());
		}
		return o;
	}


	@SuppressWarnings("rawtypes")
	public List findWhere(Class clase, String pClausulaWhere, String pOrderBy) {
		Query q;
		List listado;
		String sentenciaJPQL;
		if (pOrderBy == null || pOrderBy.length() == 0)
			sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o WHERE " + pClausulaWhere;
		else
			sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o WHERE " + pClausulaWhere + " ORDER BY " + pOrderBy;
		q = em.createQuery(sentenciaJPQL);
		listado = q.getResultList();
		mostrarLog(this.getClass(),"findWhere", clase.getSimpleName() +  sentenciaJPQL);
		return listado;
	}

	public void insertar(Object pObjeto) throws Exception {		
		if (pObjeto == null)
			throw new Exception("No se puede insertar un objeto null.");
		try {
			em.persist(pObjeto);
			mostrarLog(this.getClass(),"insertar", "Objeto insertado: " + pObjeto.getClass().getSimpleName() + " : " + pObjeto);
		} catch (Exception e) {
			mostrarLog(this.getClass(),"insertar",
					"No se pudo insertar el objeto especificado: " + pObjeto.getClass().getSimpleName() + " : " + pObjeto);
			throw new Exception("No se pudo insertar el objeto especificado: " + e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	public void eliminar(Class clase, Object pID) throws Exception {
		if (pID == null) {
			mostrarLog(this.getClass(),"eliminar",
					"Debe especificar un identificador para eliminar el dato solicitado: "
					+ clase.getSimpleName() + " : " + pID);
			throw new Exception("Debe especificar un identificador para eliminar el dato solicitado.");
		}
		Object o = findById(clase, pID);
		try {
			em.remove(o);
			mostrarLog(this.getClass(),"eliminar", "Dato eliminado: " + clase.getSimpleName() + " : " + pID);
		} catch (Exception e) {
			mostrarLog(this.getClass(),"eliminar","No se pudo eliminar el dato: " + clase.getSimpleName() + " : " + pID);
			throw new Exception("No se pudo eliminar el dato: " + e.getMessage());
		}
	}

	public void actualizar(Object pObjeto) throws Exception {
		if (pObjeto == null)
			throw new Exception("No se puede actualizar un dato null");
		try {
			em.merge(pObjeto);
			mostrarLog(this.getClass(),"actualizar", "Dato actualizado: " + pObjeto.getClass().getSimpleName() + " : " + pObjeto);
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar el dato: "
					+ e.getMessage());
		}
	}

	public EntityManager getEntityManager() {
		return em;
	}
	

	@SuppressWarnings("rawtypes")
	public List execJPQL(String pClausulaJPQL) {
		mostrarLog(this.getClass(),"execJPQL", pClausulaJPQL);
		Query q;
		List listado;
		q = em.createQuery(pClausulaJPQL);
		listado = q.getResultList();

		return listado;
	}
	
	public Long obtenerSecuenciaPostgresql(String nombreSecuencia)
			throws Exception {
		String sentenciaSQL;
		Query q;
		BigInteger valSeq;
		Long valorSeq = null;
		try {
			sentenciaSQL = "SELECT nextval('" + nombreSecuencia + "')";
			q = em.createNativeQuery(sentenciaSQL);
			valSeq = (BigInteger) q.getSingleResult();
			valorSeq = valSeq.longValue();
		} catch (Exception e) {
			mostrarLog(this.getClass(),"obtenerSecuenciaPostgresql","Error al obtener valor de secuencia ("+nombreSecuencia+") :"+e.getMessage());
			e.printStackTrace();
			throw new Exception("Error al obtener valor de secuencia ("+nombreSecuencia+") : "+e.getMessage());
		}
		mostrarLog(this.getClass(),"obtenerSecuenciaPostgresql","Valor de secuencia ("+nombreSecuencia+") :"+valorSeq);
		return valorSeq;
	}
	
	@SuppressWarnings("rawtypes")
	public long obtenerValorMax(Class clase,String nombrePropiedad) throws Exception{
		Query q;
		String sentenciaSQL;
		BigDecimal valorMax;
		try {
			sentenciaSQL="SELECT MAX(o."+nombrePropiedad+") FROM "+clase.getSimpleName()+" o";
			q = em.createQuery(sentenciaSQL);
			valorMax=(BigDecimal)q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor max: "+clase.getCanonicalName()+"["+nombrePropiedad+"]. "+e.getMessage());
		}
		if(valorMax==null)
			return 0;
		return valorMax.longValue();
	}


	@SuppressWarnings("rawtypes")
	public long obtenerValorMaxWhere(Class clase,String nombrePropiedad, String pClausulaWhere ) throws Exception{
		Query q;
		String sentenciaSQL;
		BigDecimal valorMax;
		try {
			sentenciaSQL="SELECT MAX(o."+nombrePropiedad+") FROM "+clase.getSimpleName()+" o" + " WHERE " + pClausulaWhere;
			q = em.createQuery(sentenciaSQL);
			valorMax=(BigDecimal)q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor max: "+clase.getCanonicalName()+"["+nombrePropiedad+"]. "+ pClausulaWhere+e.getMessage());
		}
		if(valorMax==null)
			return 0;
		return valorMax.longValue();
	}

}
