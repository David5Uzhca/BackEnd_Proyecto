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
import ups.edu.ec.ProyectoFinal.Bussines.GestionUsuarios;
import ups.edu.ec.ProyectoFinal.Modelo.Usuario;

@Path("usuarios")
public class UsuarioServices{
	@Inject
	private GestionUsuarios gUsuarios;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Usuario usuario) {
		try{
			gUsuarios.guardarUsuarios(usuario);
			return Response.ok(usuario).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Usuario usuario) {
		try{
			gUsuarios.actualizarUsuario(usuario);
			return Response.ok(usuario).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) {
		try{
			gUsuarios.borrarUsuario(codigo);
			return "OK";
		}catch (Exception e) {
			// TODO: handle exception
			return "Error";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response leer(@QueryParam("codigo") int codigo, @QueryParam("nombre") String nombre) {
		try{
			System.out.println("codigo" +  codigo+ " nom=" + nombre);
			Usuario usu = gUsuarios.getUsuarioPorCodigo(codigo);
			return Response.ok(usu).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Usuario no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Path("{dni}/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response leer2(@QueryParam("codigo") int codigo, @QueryParam("nombre") String nombre) {
		try{
			System.out.println("codigo" +  codigo+ " nom=" + nombre);
			Usuario usu = gUsuarios.getUsuarioPorCodigo(codigo);
			return Response.ok(usu).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Usuario no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getUsuarios(){
		List<Usuario> usuarios = gUsuarios.getUsuarios();
		if(usuarios.size()>0)
			return Response.ok(usuarios).build();
		ErrorMessage error = new ErrorMessage(6, "No se registran usuarios");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}
	
    @GET
    @Path("userbycode/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obteneruserbycode(@PathParam("codigo") int codigo) {
        try {
            Usuario usuario = gUsuarios.getUserbycode(codigo);
            if (usuario!= null) {
                return Response.ok(usuario).build();
            } else {
                ErrorMessage error = new ErrorMessage(4, "Cliente no encontrado");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
            }
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }
    
    @GET
    @Path("userbyuser/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obteneruserbyuser(@PathParam("user") String user) {
        try {
            Usuario usuario = gUsuarios.getUsuarioPorusuario(user);
            if (usuario!= null) {
                return Response.ok(usuario).build();
            } else {
                ErrorMessage error = new ErrorMessage(4, "Cliente no encontrado");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
            }
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }
    @GET
    @Path("userbyuserandpass/{user}/{contrasena}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obteneruserbyuserandpass(@PathParam("user") String user, @PathParam("contrasena") String contrasena) {
        try {
            Usuario usuario = gUsuarios.obtenerUsuarioPorUsuarioYContrasena(user, contrasena);
            if (usuario!= null) {
                return Response.ok(usuario).build();
            } else {
                ErrorMessage error = new ErrorMessage(4, "Cliente no encontrado");
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(error)
                        .build();
            }
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }
    
}
