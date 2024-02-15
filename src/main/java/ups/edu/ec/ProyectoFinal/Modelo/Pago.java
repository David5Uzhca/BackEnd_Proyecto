package ups.edu.ec.ProyectoFinal.Modelo;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pago implements Serializable{
	
	@Id
	@GeneratedValue
	private int codigo;
	
	private String metodo;
	private String numero;
	private String fecha;
	private String cvv;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	@Override
	public String toString() {
		return "Pago [codigo=" + codigo + ", metodo=" + metodo + ", numero=" + numero + ", fecha=" + fecha + ", cvv="
				+ cvv + "]";
	}
	public Pago() {

	}
	
	
		
}
