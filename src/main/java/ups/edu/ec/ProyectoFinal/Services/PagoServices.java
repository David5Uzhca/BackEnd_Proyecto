package ups.edu.ec.ProyectoFinal.Services;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.edu.ec.ProyectoFinal.Bussines.GestionPago;
import ups.edu.ec.ProyectoFinal.Modelo.Pago;

@Path("pagos")
public class PagoServices {
    @Inject
    private GestionPago gPagos;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Pago pago) {
        try {
            gPagos.guardarPago(pago);
            return Response.ok(pago).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al crear el pago");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Pago pago) {
        try {
            gPagos.actualizarPago(pago);
            return Response.ok(pago).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al actualizar el pago");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("id") int codigo) {
        try {
            gPagos.borrarPago(codigo);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("id") int codigo) {
        try {
            Pago pago = gPagos.getPagoPorCodigo(codigo);
            return Response.ok(pago).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Pago no encontrado");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPagos() {
        List<Pago> pagos = gPagos.getPagos();
        if (pagos.size() > 0)
            return Response.ok(pagos).build();
        ErrorMessage error = new ErrorMessage(6, "No se encontraron pagos");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
