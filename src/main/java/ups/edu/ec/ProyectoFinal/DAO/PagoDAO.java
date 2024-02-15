package ups.edu.ec.ProyectoFinal.DAO;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ups.edu.ec.ProyectoFinal.Modelo.Pago;

@Stateless
public class PagoDAO implements Serializable {
    
    @PersistenceContext
    private EntityManager em;

    public void insert(Pago pago) {
        em.persist(pago);
    }

    public void update(Pago pago) {
        em.merge(pago);
    }

    public void remove(int codigo) {
        Pago pago = em.find(Pago.class, codigo);
        em.remove(pago);
    }

    public Pago read(int codigo) {
        Pago pago = em.find(Pago.class, codigo);
        return pago;
    }

    public List<Pago> getAll() {
        String jpql = "SELECT p FROM Pago p";
        Query q = em.createQuery(jpql, Pago.class);
        return q.getResultList();
    }

    public Pago getPagoPorCodigo(int codigo) {
        String jpql = "SELECT p FROM Pago p WHERE p.codigo = :codigo";
        Query q = em.createQuery(jpql, Pago.class);
        q.setParameter("codigo", codigo);
        List<Pago> pagos = q.getResultList();
        if (pagos.size() > 0) {
            return pagos.get(0);
        }
        return null;
    }
}
