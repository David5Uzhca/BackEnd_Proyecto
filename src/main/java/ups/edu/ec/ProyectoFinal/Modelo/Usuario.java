package ups.edu.ec.ProyectoFinal.Modelo;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue
	private int codigo;  
	
	private String nombre;
	private String usuario;
	private String correo;
	private String contrasena;
	

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nombre=" + nombre + ", usuario=" + usuario + ", correo=" + correo
				+ ", contrasena=" + contrasena + "]";
	}
	public Usuario() {

	}
    public Usuario(int codigo, String nombre, String usuario, String correo, String contrasena) {
        this.codigo = codigo;
    	this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;  
        this.contrasena = contrasena;
    }
	
	
}
