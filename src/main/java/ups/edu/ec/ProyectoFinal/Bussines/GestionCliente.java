package ups.edu.ec.ProyectoFinal.Bussines;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.ProyectoFinal.DAO.ClienteDAO;
import ups.edu.ec.ProyectoFinal.Modelo.Cliente;

@Stateless
public class GestionCliente {
    @Inject
    private ClienteDAO clienteDAO;

    public void guardarCliente(Cliente cliente) {
        Cliente cli = clienteDAO.read(cliente.getCodigo());
        if (cli != null) {
            clienteDAO.update(cliente);
        } else {
            clienteDAO.insert(cliente);
        }
    }

    public void actualizarCliente(Cliente cliente) throws Exception {
        Cliente cli = clienteDAO.read(cliente.getCodigo());
        if (cli != null) {
            clienteDAO.update(cliente);
        } else {
            throw new Exception("Cliente no existe");
        }
    }

    public Cliente getClientePorCodigo(int codigo) throws Exception {
        return clienteDAO.getClientePorCodigo(codigo);
    }

    public void borrarCliente(int codigo) {
        clienteDAO.remove(codigo);
    }

    public List<Cliente> getClientes() {
        return clienteDAO.getAll();
    }
}
