package um.edu.uy.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.uy.business.entities.Client;
import um.edu.uy.business.exceptions.ClientAlreadyExists;
import um.edu.uy.business.exceptions.InvalidClientInformation;
import um.edu.uy.persistence.ClientRepository;

@Service
public class ClientMgr {

    @Autowired
    private ClientRepository clientRepository;

    public void addClient(Client client)
            throws InvalidClientInformation, ClientAlreadyExists {

        if (client.getName() == null || "".equals(client.getName()) || client.getAddress() == null || "".equals(client.getAddress())) {

            throw new InvalidClientInformation("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (clientRepository.findOneByDocument(client.getDocument()) != null) {

            throw new ClientAlreadyExists();
        }

        clientRepository.save(client);

    }


}
