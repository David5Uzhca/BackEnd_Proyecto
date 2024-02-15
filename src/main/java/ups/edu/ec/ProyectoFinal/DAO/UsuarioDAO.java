package ups.edu.ec.ProyectoFinal.DAO;


import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ups.edu.ec.ProyectoFinal.Modelo.Usuario;

@Stateless
public class UsuarioDAO implements Serializable{ 

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Usuario usuario) {
		em.persist(usuario);
	}
	
	public void update(Usuario usuario) {
		em.merge(usuario);
	}
	
	public void remove(int codigo) {
		Usuario usuario = em.find(Usuario.class, codigo);
		em.remove(usuario);
	}
	
	public Usuario read(int codigo) {
		Usuario usuario = em.find(Usuario.class, codigo);
		return usuario;
	}
	
	public List<Usuario> getAll(){
	    String jpql = "SELECT u FROM Usuario u";
	    Query q = em.createQuery(jpql, Usuario.class);
	    return q.getResultList();
	}	

	
	public Usuario getUsuarioPorCodigo(int codigo){
		String jpql = "SELECT c FROM Usuario c WHERE c.codigo = :codigo";
		Query q = em.createQuery(jpql, Usuario.class);
		q.setParameter("codigo", codigo);
		List<Usuario> usuarios = q.getResultList();
		if(usuarios.size()>0)
			return usuarios.get(0);
		return null;
	} 
	
	
    public Usuario getSaldoYDniPorId(int codigo) {
        String jpql = "SELECT NEW ups.edu.ec.ProyectoFinal.Modelo.Usuario(c.codigo, c.nombre, c.usuario, c.correo, c.contrasena) FROM Usuario c WHERE c.codigo = :codigo";
        Query q = em.createQuery(jpql, Usuario.class);
        q.setParameter("codigo", codigo);
        List<Usuario> usuarios= q.getResultList(); 
        if (usuarios.size() > 0) {
            return usuarios.get(0);
        }
        return null;
    }
    
    public Usuario getUsuarioPorUsuario(String nombreUsuario){
        String jpql = "SELECT u FROM Usuario u WHERE u.usuario = :nombreUsuario";
        Query q = em.createQuery(jpql, Usuario.class);
        q.setParameter("nombreUsuario", nombreUsuario);
        List<Usuario> usuarios = q.getResultList();
        if(usuarios.size() > 0) {
            return usuarios.get(0);
        }
        return null;
    }
    public Usuario getUsuarioPorUsuarioYContrasena(String nombreUsuario, String contrasena) {
        String jpql = "SELECT u FROM Usuario u WHERE u.usuario = :nombreUsuario AND u.contrasena = :contrasena";
        Query q = em.createQuery(jpql, Usuario.class);
        q.setParameter("nombreUsuario", nombreUsuario);
        q.setParameter("contrasena", contrasena);
        List<Usuario> usuarios = q.getResultList();
        if(usuarios.size() > 0) {
            return usuarios.get(0);
        }
        return null;
    }

}
