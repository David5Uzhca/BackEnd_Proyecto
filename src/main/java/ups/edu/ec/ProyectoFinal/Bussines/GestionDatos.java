package ups.edu.ec.ProyectoFinal.Bussines;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import ups.edu.ec.ProyectoFinal.DAO.ClienteDAO;
import ups.edu.ec.ProyectoFinal.DAO.FacturaDAO;
import ups.edu.ec.ProyectoFinal.DAO.PagoDAO;
import ups.edu.ec.ProyectoFinal.DAO.ProductoDAO;
import ups.edu.ec.ProyectoFinal.DAO.UsuarioDAO;
import ups.edu.ec.ProyectoFinal.Modelo.Cliente;
import ups.edu.ec.ProyectoFinal.Modelo.Detalle;
import ups.edu.ec.ProyectoFinal.Modelo.Factura;
import ups.edu.ec.ProyectoFinal.Modelo.Pago;
import ups.edu.ec.ProyectoFinal.Modelo.Producto;
import ups.edu.ec.ProyectoFinal.Modelo.Usuario;

@Singleton
@Startup  
public class GestionDatos {
    @Inject
    private UsuarioDAO daoUsuario;

    @Inject
    private FacturaDAO daoFactura;

    @Inject
    private ClienteDAO daoCliente;

    @Inject
    private ProductoDAO daoProducto;
    
    @Inject
    private PagoDAO daoPago;

    @PostConstruct
    public void init() {
        System.out.println("Iniciando carga de datos...");

        try {
        	
            // Inicialización de usuario
            Usuario usuario = new Usuario();
            usuario.setNombre("admin");
            usuario.setUsuario("admin");
            usuario.setCorreo("admin@ej.com");
            usuario.setContrasena("admin");
            daoUsuario.insert(usuario);

            // Inicialización de productos
            Producto producto = new Producto();
	            producto.setNombre("Producto Ejemplo");
	            producto.setDescripcion("Descripción del producto");
	            producto.setCodigo(1);
	            producto.setIva(12.5);  
	            producto.setMarca("Pumba");
	            producto.setPeso(19.5);
	            producto.setPrecio(15);
	            producto.setStock(150);
	            producto.setTalla("G");
            daoProducto.insert(producto);

            Producto producto2 = new Producto();
            producto2.setNombre("Producto Ejemplo2");
            producto2.setDescripcion("Descripción del producto");
            producto2.setCodigo(2);
            producto2.setIva(12.5);  
            producto2.setMarca("Pumba");
            producto2.setPeso(19.5);
            producto2.setPrecio(15);
            producto2.setStock(150);
            producto2.setTalla("G");
            daoProducto.insert(producto2);

            // Inicialización de clientes
            Cliente cliente = new Cliente();
            cliente.setCedula("0302010605");
            cliente.setNombre("Erika");
            cliente.setCorreo("eri@example.com");
            cliente.setCiudad("Cuenca");
            cliente.setDireccion("la loma");
            cliente.setTelefono("0987654321");
            daoCliente.insert(cliente);

            Cliente cliente2 = new Cliente();
            cliente2.setCedula("0302447347");
            cliente2.setNombre("David");
            cliente2.setCorreo("david@example.com");
            cliente2.setCiudad("Cuenca");
            cliente2.setDireccion("la loma");
            cliente2.setTelefono("0987654321");
            daoCliente.insert(cliente2);
            
            // Crear Pago
            Pago pago = new Pago();
            pago.setCvv("192");
            pago.setFecha("15/05");
            pago.setMetodo("VISA");
            pago.setNumero("003251569220");
            daoPago.insert(pago);
           
            // Crear detalle 
            Detalle detalle = new Detalle();
            //detalle.setUsuario(usuario);
            //detalle.addProducto(producto2);
            //detalle.addProducto(producto);
            detalle.setPrecio(0.00);
            detalle.setCantidad(0);
            detalle.setSubtotal(0.00);

            // Inicialización de facturas
            Factura factura = new Factura();
            factura.setCodigo(1);
            factura.setFecha(new Date());
            factura.setSubtotal(0.0);
            factura.setIva(0.0);
            factura.setTotal(0.0);
            factura.setDetalle(detalle);
            factura.setPago(pago);
            factura.setCliente(cliente);
            daoFactura.insert(factura);

            
            System.out.println("Carga de datos completada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
