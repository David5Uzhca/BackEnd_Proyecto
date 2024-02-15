package ups.edu.ec.ProyectoFinal.DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ups.edu.ec.ProyectoFinal.Modelo.Detalle;
import ups.edu.ec.ProyectoFinal.Modelo.Factura;
import ups.edu.ec.ProyectoFinal.Modelo.Producto;

@Stateless
public class ProductoDAO implements Serializable {
	Producto producto = new Producto();
	Detalle detalle = new Detalle();
    
    @PersistenceContext
    private EntityManager em;

    public void insert(Producto producto) {
        em.persist(producto);
    }

    public void update(Producto producto) {
        em.merge(producto);
    }
    public void eliminarPorCodigo(int codigo) {
        Producto producto = em.find(Producto.class, codigo);
        if (producto != null) {
            em.remove(producto);
        } else {
            throw new IllegalArgumentException("No se encontró ningún producto con el código proporcionado");
        }
    }
    public Producto read(int codigo) {
        Producto producto = em.find(Producto.class, codigo);
        return producto;
    }
	public void remove(int codigo) {
		Producto producto = em.find(Producto.class, codigo);
		em.remove(producto);
	}
	

    public List<Producto> getAll() {
        String jpql = "SELECT p FROM Producto p";
        Query q = em.createQuery(jpql, Producto.class);
        return q.getResultList();
    }

    public Producto getProductoPorCodigo(int codigo) {
        String jpql = "SELECT p FROM Producto p WHERE p.codigo = :codigo";
        Query q = em.createQuery(jpql, Producto.class);
        q.setParameter("codigo", codigo);
        List<Producto> productos = q.getResultList();
        if (productos.size() > 0) {
            return productos.get(0);
        }
        return null;
    }
    public Producto obtenerUltimoProducto() {
        String jpql = "SELECT p FROM Producto p ORDER BY p.codigo DESC";
        Query q = em.createQuery(jpql, Producto.class);
        q.setMaxResults(1); 
        List<Producto> productos = q.getResultList();
        if (!productos.isEmpty()) {
            return productos.get(0);
        }
        return null;
    }
    public void setSubtotalFactura(Producto productos) {
    	int facturaCodigo = 1;
        Factura factura= em.find(Factura.class, facturaCodigo);
        if (factura != null) {
            factura.setSubtotal(factura.getSubtotal()-productos.getPrecio());
            em.merge(factura);
        } else {
            System.out.println("No se encontró el detalle con el código especificado.");
        }
    }

    
}
