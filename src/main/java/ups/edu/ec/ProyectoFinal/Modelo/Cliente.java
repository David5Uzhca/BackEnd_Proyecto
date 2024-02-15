package ups.edu.ec.ProyectoFinal.Modelo;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cliente implements Serializable {
	
	@Id
	@GeneratedValue
	private int codigo;
	private String cedula;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String correo;
	private String telefono;
	
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", cedula=" + cedula + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", ciudad=" + ciudad + ", correo=" + correo + ", telefono=" + telefono + "]";
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public Cliente() {

	}
	
}
