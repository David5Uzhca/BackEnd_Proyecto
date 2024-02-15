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
import ups.edu.ec.ProyectoFinal.Bussines.GestionFactura;
import ups.edu.ec.ProyectoFinal.Modelo.Cliente;
import ups.edu.ec.ProyectoFinal.Modelo.Factura;
import ups.edu.ec.ProyectoFinal.Modelo.Pago;
import ups.edu.ec.ProyectoFinal.Modelo.Producto;

@Path("facturas")
public class FacturaServices {
    @Inject
    private GestionFactura gFacturas;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Factura factura) {
        try {
            gFacturas.guardarFactura(factura);
            return Response.ok(factura).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al crear la factura");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Factura factura) {
        try {
            gFacturas.actualizarFactura(factura);
            return Response.ok(factura).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al actualizar la factura");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("id") int codigo) {
        try {
            gFacturas.borrarFactura(codigo);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("id") int codigo) {
        try {
            Factura factura = gFacturas.getFacturaPorCodigo(codigo);
            return Response.ok(factura).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Factura no encontrada");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacturas() {
        List<Factura> facturas = gFacturas.getFacturas();
        if (facturas.size() > 0)
            return Response.ok(facturas).build();
        ErrorMessage error = new ErrorMessage(6, "No se encontraron facturas");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
    @POST
    @Path("agregarCliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarClienteAFactura(@QueryParam("codigoFactura") int codigoFactura, Cliente cliente) {
        try {
            gFacturas.agregarClienteAFactura(codigoFactura, cliente);
            return Response.ok().build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al agregar Cliente a la Factura");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }
    @POST
    @Path("agregarPago")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregarPagoAFactura(@QueryParam("codigoFactura") int codigoFactura, Pago pago) {
        try {
            gFacturas.agregarPagoAFactura(codigoFactura, pago);
            return Response.ok().build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al agregar Pago a la Factura");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

}
