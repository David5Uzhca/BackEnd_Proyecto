package ups.edu.ec.ProyectoFinal.DAO;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ups.edu.ec.ProyectoFinal.Modelo.Cliente;
import ups.edu.ec.ProyectoFinal.Modelo.Detalle;
import ups.edu.ec.ProyectoFinal.Modelo.Factura;
import ups.edu.ec.ProyectoFinal.Modelo.Pago;
import ups.edu.ec.ProyectoFinal.Modelo.Producto;

import java.io.Serializable;
import java.util.List;

@Stateless
public class FacturaDAO implements Serializable{
	Detalle detalle = new Detalle();

    @PersistenceContext
    private EntityManager em;

    public void insert(Factura factura) {
        em.persist(factura);
    }

    public void update(Factura factura) {
        em.merge(factura);
    }

    public void remove(int codigo) {
        Factura factura = em.find(Factura.class, codigo);
        em.remove(factura);
    }

    public Factura read(int codigo) {
        return em.find(Factura.class, codigo);
    }

    public List<Factura> getAll() {
        String jpql = "SELECT f FROM Factura f";
        Query q = em.createQuery(jpql, Factura.class);
        return q.getResultList();
    }
    
    public void agregarClienteAFactura(int codigoFactura, Cliente cliente) {
    	Factura factura = em.find(Factura.class, codigoFactura);   	
        if (factura != null) {
            factura.setCliente(cliente);
            em.merge(factura);
        } else {
            System.out.println("No se encontró el detalle con el código: " + codigoFactura);
        }
    }
    public void agregarPagoAFactura(int codigoFactura, Pago pago) {
    	
    	Factura factura = em.find(Factura.class, codigoFactura);   	
        if (factura != null) {
            factura.setPago(pago);
            em.merge(factura);
        } else {
            System.out.println("No se encontró el detalle con el código: " + codigoFactura);
        }
    }
    
    
    public void setSubtotalFactura() {
    	int facturaCodigo = 1;
        Factura factura= em.find(Factura.class, facturaCodigo);
        if (factura != null) {
            factura.setSubtotal(detalle.getSubtotal());
            factura.setSubtotal(detalle.getCantidad());
            factura.setSubtotal(detalle.getPrecio());
            em.merge(factura);
        } else {
            System.out.println("No se encontró el detalle con el código especificado.");
        }
    }

    
    
}
