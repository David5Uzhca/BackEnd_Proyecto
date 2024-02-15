package ups.edu.ec.ProyectoFinal.Bussines;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.ProyectoFinal.DAO.ProductoDAO;
import ups.edu.ec.ProyectoFinal.Modelo.Producto;
import ups.edu.ec.ProyectoFinal.Modelo.Usuario;

@Stateless
public class GestionProducto {
    @Inject
    private ProductoDAO productoDAO;

    /*public void guardarProducto(Producto producto) {
        // Verificar si el producto ya existe en la base de datos
        Producto prod = productoDAO.read(producto.getCodigo());
        if (prod != null) {
            // El producto ya existe, actualizarlo
            productoDAO.update(producto);
        } else {
            // El producto no existe, asignar un nuevo código y guardarlo
            // Aquí podrías generar un nuevo código de producto de manera adecuada
            // por ejemplo, consultando el último código utilizado y aumentándolo en 1
            // o utilizando algún otro método para generar códigos únicos
            int nuevoCodigo = generarNuevoCodigo(); // Implementa este método según tus necesidades

            // Asignar el nuevo código al producto antes de guardarlo
            producto.setCodigo(nuevoCodigo);
            
            // Persistir el producto en la base de datos
            productoDAO.insert(producto);
        }
    }*/
	public void guardarProducto(Producto producto) {
        Producto prod = productoDAO.read(producto.getCodigo());
        if (prod != null) {
            productoDAO.update(producto);
		}else {
			productoDAO.insert(producto);
		}
	}


    public void actualizarProducto(Producto producto) throws Exception {
        Producto prod = productoDAO.read(producto.getCodigo());
        if (prod != null) {
            productoDAO.update(producto);
        } else {
            throw new Exception("Producto no existe");
        }
    }

    public Producto getProductoPorCodigo(int codigo) throws Exception {
        return productoDAO.getProductoPorCodigo(codigo);
    }

    public void borrarProducto(int codigo) {
        productoDAO.eliminarPorCodigo(codigo);
    }

    public List<Producto> getProductos() {
        return productoDAO.getAll();
    }
	public void borrarProductos(int codigo) {
		productoDAO.remove(codigo);
	}
    public int generarNuevoCodigo() {
        Producto ultimoProducto = productoDAO.obtenerUltimoProducto();
        if (ultimoProducto != null) {
            return ultimoProducto.getCodigo() + 1;
        } else {
            return 1;
        }
    }

}
