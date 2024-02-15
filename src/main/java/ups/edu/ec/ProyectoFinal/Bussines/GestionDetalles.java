package ups.edu.ec.ProyectoFinal.Bussines;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.ProyectoFinal.DAO.DetalleDAO;
import ups.edu.ec.ProyectoFinal.Modelo.Detalle;
import ups.edu.ec.ProyectoFinal.Modelo.Producto;
import ups.edu.ec.ProyectoFinal.Modelo.Usuario;

@Stateless
public class GestionDetalles {
	
	    @Inject
	    private DetalleDAO daoDetalle;

	    public void guardarDetalle(Detalle detalle) {
	        daoDetalle.insert(detalle);
	    }

	    public void actualizarDetalle(Detalle detalle) {
	        daoDetalle.update(detalle);
	    }

	    public Detalle getDetallePorCodigo(int codigo) {
	        return daoDetalle.read(codigo);
	    }

	    public void borrarDetalle(int codigo) {
	        daoDetalle.remove(codigo);
	    }

	    public List<Detalle> getDetalles() {
	        return daoDetalle.getAll();
	    }
	    
	    public Usuario getDetailbycode(int codigo) {
	        return daoDetalle.getDetallePorCodigo(codigo);
	    }
	    
	    public List<Producto> getProductosbycode(int codigo){
	    	return daoDetalle.getProductosPorDetalle(codigo);
	    	
	    }
	    
	    public void agregarProductoADetalle(int codigoDetalle, Producto producto) {
	        daoDetalle.agregarProductoADetalle(codigoDetalle, producto);
	    }
	    
	    public void setSubtotalDetalle(int codigoDetalle, double nuevoSubtotal) {
	        daoDetalle.setSubtotalDetalle(codigoDetalle, nuevoSubtotal);
	    }
}
