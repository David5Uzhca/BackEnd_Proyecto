package ups.edu.ec.ProyectoFinal.Bussines;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.ProyectoFinal.DAO.UsuarioDAO;
import ups.edu.ec.ProyectoFinal.Modelo.Usuario;

@Stateless
public class GestionUsuarios{
	@Inject                             
	private UsuarioDAO daoUsuario;

	public void guardarUsuarios(Usuario usuario) {
		Usuario cli = daoUsuario.read(usuario.getCodigo());
		if (cli != null){
			daoUsuario.update(usuario);
		}else {
			daoUsuario.insert(usuario);
		}
	}
	
	public void actualizarUsuario(Usuario usuario) throws Exception {
		Usuario cli = daoUsuario.read(usuario.getCodigo());
		if (cli != null){
			daoUsuario.update(usuario);
		}else {
			throw new Exception("Usuario no existe");
		}
	}
	
	public Usuario getUsuarioPorCodigo(int codigo) throws Exception{	
		return daoUsuario.getUsuarioPorCodigo(codigo);
	}
	
	public Usuario getUsuarioPorusuario(String usuario) throws Exception{	
		return daoUsuario.getUsuarioPorUsuario(usuario);
	}
	
	public void borrarUsuario(int codigo){
		daoUsuario.remove(codigo);
	}
	
	public List<Usuario> getUsuarios(){
		return daoUsuario.getAll();
	}
    public Usuario getUserbycode(int codigo) {
        return daoUsuario.getSaldoYDniPorId(codigo);
    }
    
    public Usuario obtenerUsuarioPorUsuarioYContrasena(String nombreUsuario, String contrasena) {
        return daoUsuario.getUsuarioPorUsuarioYContrasena(nombreUsuario, contrasena);
    }
}