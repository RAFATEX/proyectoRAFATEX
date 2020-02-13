package rafa.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import rafa.model.entities.FacturaCab;
import rafa.model.entities.FacturaDet;
import rafa.model.entities.Producto;

/**
 * Session Bean implementation class ManagerFactDet
 */
@Stateless
@LocalBean
public class ManagerFactDet {

    private EntityManager em;
    
    public ManagerFactDet() {
        // TODO Auto-generated constructor stub
    }
    
    public List<FacturaDet>findAllFdetalle(){
    	String consulta = "SELECT f FROM FacturaDet f";
    	Query q = em.createQuery(consulta, FacturaDet.class);
    	return q.getResultList();
    }
    
    public FacturaDet findAllFdetalleById(Integer id ) {
    	return em.find(FacturaDet.class, id);
    }
    
    public void insertarFac(FacturaDet factura, Producto pr, FacturaCab fc ) {
    	FacturaDet fd = new FacturaDet();
    
    	fd.setCantidad(factura.getCantidad());
    	fd.setPrecioUnitarioVenta(pr.getPrecioUnitario());
    	fd.setProducto(pr);
    	fd.setFacturaCab(fc);
    	
    	em.persist(fd);
    }
    
    public void eliminarFac(Integer id ) {
    	FacturaDet fd = findAllFdetalleById(id);
    	if(fd!=null)
    		em.remove(fd);
    }
    
    public void actualizadF(FacturaDet ft) throws Exception{
    	
    }

}
