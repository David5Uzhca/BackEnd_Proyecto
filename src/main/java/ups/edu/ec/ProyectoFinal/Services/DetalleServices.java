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
import ups.edu.ec.ProyectoFinal.Bussines.GestionDetalles;
import ups.edu.ec.ProyectoFinal.Modelo.Detalle;
import ups.edu.ec.ProyectoFinal.Modelo.Producto;

@Path("detalles")
public class DetalleServices {
    @Inject
    private GestionDetalles gDetalles;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Detalle detalle) {
        try {
            gDetalles.guardarDetalle(detalle);
            return Response.ok(detalle).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al crear el detalle");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Detalle detalle) {
        try {
            gDetalles.actualizarDetalle(detalle);
            return Response.ok(detalle).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al actualizar el detalle");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("id") int codigo) {
        try {
            gDetalles.borrarDetalle(codigo);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("id") int codigo) {
        try {
            Detalle detalle = gDetalles.getDetallePorCodigo(codigo);
            return Response.ok(detalle).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Detalle no encontrado");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalles() {
        List<Detalle> detalles = gDetalles.getDetalles();
        if (detalles.size() > 0)
            return Response.ok(detalles).build();
        ErrorMessage error = new ErrorMessage(6, "No se encontraron detalles");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }

    @GET
    @Path("productos/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosPorDetalle(@PathParam("codigo") int codigoDetalle) {
        try {
            List<Producto> productos = gDetalles.getProductosbycode(codigoDetalle);
            if (productos != null && !productos.isEmpty()) {
                return Response.ok(productos).build();
            } else {
                ErrorMessage error = new ErrorMessage(7, "No se encontraron productos para el detalle con código " + codigoDetalle);
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
            }
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(8, "Error al obtener los productos para el detalle con código " + codigoDetalle);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }    

    @POST
    @Path("agregarProducto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarProductoADetalle(@QueryParam("codigoDetalle") int codigoDetalle, Producto producto) {
        try {
            gDetalles.agregarProductoADetalle(codigoDetalle, producto);
            return Response.ok().build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al agregar producto al detalle");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    } 
    
    @POST
    @Path("{codigoDetalle}/{subtotal}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setSubtotalDetalle(@PathParam("codigoDetalle") int codigoDetalle, @PathParam("subtotal") double nuevoSubtotal) {
        gDetalles.setSubtotalDetalle(codigoDetalle, nuevoSubtotal);
        return Response.ok().build();
    }

}
