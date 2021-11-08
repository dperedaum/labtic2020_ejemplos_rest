package um.edu.uy.persistence;

import org.springframework.data.repository.CrudRepository;
import um.edu.uy.business.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param document
     * @return
     */
    Client findOneByDocument(long document);

}
