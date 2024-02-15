package ups.edu.ec.ProyectoFinal.Bussines;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.ProyectoFinal.DAO.PagoDAO;
import ups.edu.ec.ProyectoFinal.Modelo.Pago;

@Stateless
public class GestionPago {
    @Inject
    private PagoDAO pagoDAO;

    public void guardarPago(Pago pago) {
        Pago pag = pagoDAO.read(pago.getCodigo());
        if (pag != null) {
            pagoDAO.update(pago);
        } else {
            pagoDAO.insert(pago);
        }
    }

    public void actualizarPago(Pago pago) throws Exception {
        Pago pag = pagoDAO.read(pago.getCodigo());
        if (pag != null) {
            pagoDAO.update(pago);
        } else {
            throw new Exception("Pago no existe");
        }
    }

    public Pago getPagoPorCodigo(int codigo) throws Exception {
        return pagoDAO.getPagoPorCodigo(codigo);
    }

    public void borrarPago(int codigo) {
        pagoDAO.remove(codigo);
    }

    public List<Pago> getPagos() {
        return pagoDAO.getAll();
    }
}
