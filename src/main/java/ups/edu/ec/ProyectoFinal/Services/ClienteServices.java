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
import ups.edu.ec.ProyectoFinal.Bussines.GestionCliente;
import ups.edu.ec.ProyectoFinal.Modelo.Cliente;

@Path("clientes")
public class ClienteServices {
    @Inject
    private GestionCliente gClientes;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Cliente cliente) {
        try {
            gClientes.guardarCliente(cliente);
            return Response.ok(cliente).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al crear el cliente");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Cliente cliente) {
        try {
            gClientes.actualizarCliente(cliente);
            return Response.ok(cliente).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(1, "Error al actualizar el cliente");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String borrar(@QueryParam("id") int codigo) {
        try {
            gClientes.borrarCliente(codigo);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@QueryParam("id") int codigo) {
        try {
            Cliente cliente = gClientes.getClientePorCodigo(codigo);
            return Response.ok(cliente).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Cliente no encontrado");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientes() {
        List<Cliente> clientes = gClientes.getClientes();
        if (clientes.size() > 0)
            return Response.ok(clientes).build();
        ErrorMessage error = new ErrorMessage(6, "No se encontraron clientes");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
