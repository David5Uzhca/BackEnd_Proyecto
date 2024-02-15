package ups.edu.ec.ProyectoFinal.Modelo;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Factura implements Serializable {
	
    @Id
    private int codigo;
    private Date fecha;
    private double subtotal;
    private double iva;
    private double total;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Detalle detalle;

    @ManyToOne
    private Cliente cliente;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pago pago;

    public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
	public String toString() {
		return "Factura [codigo=" + codigo + ", fecha=" + fecha + ", subtotal=" + subtotal + ", iva=" + iva + ", total="
				+ total + ", detalle=" + detalle + ", cliente=" + cliente + ", pago=" + pago + "]";
	}

	public Factura() {

    }
    
    
}
