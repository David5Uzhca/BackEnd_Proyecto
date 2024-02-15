package ups.edu.ec.ProyectoFinal.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Detalle implements Serializable {
	
    @Id
    @GeneratedValue
    private int codigo;  
    private double precio;
    private double cantidad;
    private double subtotal;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Producto> productos;

    @ManyToOne
    private Factura factura;

    @ManyToOne
    private Usuario usuario;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public void addProducto(Producto producto) {
        if(productos == null) {
            productos = new ArrayList<>();
        }
        productos.add(producto);
    }
	

	@Override
	public String toString() {
		return "Detalle [codigo=" + codigo + ", precio=" + precio + ", cantidad=" + cantidad + ", subtotal=" + subtotal
				+ ", productos=" + productos + ", factura=" + factura + ", usuario=" + usuario + "]";
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Detalle() {

	}
	
}
