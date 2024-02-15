package ups.edu.ec.ProyectoFinal.Services;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.edu.ec.ProyectoFinal.Bussines.GestionProducto;
import ups.edu.ec.ProyectoFinal.Modelo.Producto;

@Path("productos")
public class ProductoServices {
    @Inject
    private GestionProducto gProductos;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Producto producto) {
        try {
            gProductos.guardarProducto(producto);
            return Response.ok(producto).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al crear el producto");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Producto producto) {
        try {
            gProductos.actualizarProducto(producto);
            return Response.ok(producto).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al actualizar el producto");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("codigo") int codigo) {
		try{
			gProductos.borrarProductos(codigo);
			return "OK";
		}catch (Exception e) {
			// TODO: handle exception
			return "Error";
		}
	}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("codigo") int codigo) {
        try {
            Producto producto = gProductos.getProductoPorCodigo(codigo);
            return Response.ok(producto).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Producto no encontrado");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos() {
        List<Producto> productos = gProductos.getProductos();
        if (productos.size() > 0)
            return Response.ok(productos).build();
        ErrorMessage error = new ErrorMessage(6, "No se encontraron productos");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
    
    @DELETE
    @Path("borrarProd/{codigo}") // El código del producto se pasa como parte de la URL
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String borrarprod(@PathParam("codigo") int codigo) { // Utiliza @PathParam para acceder al código desde la URL
        try {
            gProductos.borrarProductos(codigo);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el stack trace para identificar posibles errores
            return "Error";
        }
    }

}
