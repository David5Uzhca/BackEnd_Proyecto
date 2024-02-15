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
import ups.edu.ec.ProyectoFinal.Modelo.Usuario;

@Stateless
public class DetalleDAO implements Serializable{
	
	Producto producto = new Producto();

	double precio = 0;

    @PersistenceContext
    private EntityManager em;

    public void insert(Detalle detalle) {
        em.persist(detalle);
    }

    public void update(Detalle detalle) {
        em.merge(detalle);
    }

    public void remove(int codigo) {
        Detalle detalle = em.find(Detalle.class, codigo);
        em.remove(detalle);
    }

    public Detalle read(int codigo) {
        return em.find(Detalle.class, codigo);
    }

    public List<Detalle> getAll() {
        String jpql = "SELECT d FROM Detalle d";
        Query q = em.createQuery(jpql, Detalle.class);
        return q.getResultList();
    }
    
    public Usuario getDetallePorCodigo(int codigo) {
        String jpql = "SELECT NEW ups.edu.ec.ProyectoFinal.Modelo.Usuario(c.codigo, c.nombre, c.usuario, c.correo, c.contrasena) FROM Usuario c WHERE c.codigo = :codigo";
        Query q = em.createQuery(jpql, Usuario.class);
        q.setParameter("codigo", codigo);
        List<Usuario> usuarios= q.getResultList(); 
        if (usuarios.size() > 0) {
            return usuarios.get(0);
        }
        return null;
    }
    public List<Producto> getProductosPorDetalle(int codigoDetalle) {
        String jpql = "SELECT d.productos FROM Detalle d WHERE d.codigo = :codigoDetalle";
        Query q = em.createQuery(jpql, Producto.class);
        q.setParameter("codigoDetalle", codigoDetalle);
        List<Producto> productos = q.getResultList();
        return productos;
    }
    
    public void agregarProductoADetalle(int codigoDetalle, Producto producto) {
    	Detalle detalle = em.find(Detalle.class, codigoDetalle);
    	
    	setSubtotalDetalles(producto.getPrecio());
    	setValoresDetalles();
    	setSubtotalFactura(detalle);
        if (detalle != null) {
            detalle.addProducto(producto);
            em.merge(detalle);
        } else {
            System.out.println("No se encontró el detalle con el código: " + codigoDetalle);
        }
    }
    
    public void setSubtotalDetalle(int codigoDetalle, double nuevoSubtotal) {
        Detalle detalle = em.find(Detalle.class, codigoDetalle);
        if (detalle != null) {
            detalle.setSubtotal(nuevoSubtotal);
            em.merge(detalle);
        } else {
            System.out.println("No se encontró el detalle con el código: " + codigoDetalle);
        }
    }
    
    public void setSubtotalDetalles(double precioProducto) {
    	int detalleCodigo = 1;
        Detalle detalle = em.find(Detalle.class, detalleCodigo);
        if (detalle != null) {
            detalle.setSubtotal(detalle.getSubtotal() + precioProducto);
            em.merge(detalle);
        } else {
            System.out.println("No se encontró el detalle con el código especificado.");
        }
    }
    
    public void setValoresDetalles() {
    	int detalleCodigo = 1;
        Detalle detalle = em.find(Detalle.class, detalleCodigo);
        if (detalle != null) {
            detalle.setCantidad(detalle.getSubtotal()*0.12);
            detalle.setPrecio(detalle.getCantidad()+detalle.getSubtotal());
            em.merge(detalle);
        } else {
            System.out.println("No se encontró el detalle con el código especificado.");
        }
    }
    public void setSubtotalFactura(Detalle detalle) {
    	int facturaCodigo = 1;
        Factura factura= em.find(Factura.class, facturaCodigo);
        if (factura != null) {
            factura.setSubtotal(detalle.getSubtotal());
            factura.setIva(detalle.getCantidad());
            factura.setTotal(detalle.getPrecio());
            em.merge(factura);
        } else {
            System.out.println("No se encontró el detalle con el código especificado.");
        }
    }
}
