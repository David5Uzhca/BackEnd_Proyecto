package ups.edu.ec.ProyectoFinal.Bussines;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.ProyectoFinal.DAO.FacturaDAO;
import ups.edu.ec.ProyectoFinal.Modelo.Cliente;
import ups.edu.ec.ProyectoFinal.Modelo.Factura;
import ups.edu.ec.ProyectoFinal.Modelo.Pago;
import ups.edu.ec.ProyectoFinal.Modelo.Producto;

@Stateless
public class GestionFactura {
	 @Inject
	    private FacturaDAO daoFactura;

	    public void guardarFactura(Factura factura) {
	        daoFactura.insert(factura);
	    }

	    public void actualizarFactura(Factura factura) {
	        daoFactura.update(factura);
	    }

	    public Factura getFacturaPorCodigo(int codigo) {
	        return daoFactura.read(codigo);
	    }

	    public void borrarFactura(int codigo) {
	        daoFactura.remove(codigo);
	    }

	    public List<Factura> getFacturas() {
	        return daoFactura.getAll();
	    }
	    
	    public void agregarClienteAFactura(int codigoDetalle, Cliente cliente) {
	        daoFactura.agregarClienteAFactura(codigoDetalle, cliente);
	    }
	    
	    public void agregarPagoAFactura(int codigoDetalle, Pago pago) {
	        daoFactura.agregarPagoAFactura(codigoDetalle, pago);
	    }
	    
}
