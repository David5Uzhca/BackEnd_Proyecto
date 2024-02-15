package ups.edu.ec.ProyectoFinal.Modelo;

import java.io.Serializable;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Producto implements Serializable {

	@Id
	private int codigo;
	private String nombre;
	private String descripcion;
	private double precio;
	private double iva;
	private String talla;
	private int stock;
	private String marca;
	private double peso;
	
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio="
				+ precio + ", iva=" + iva + ", talla=" + talla + ", stock=" + stock + ", marca=" + marca + ", peso="
				+ peso + "]";
	}
	public Producto() {

	}
	
}
