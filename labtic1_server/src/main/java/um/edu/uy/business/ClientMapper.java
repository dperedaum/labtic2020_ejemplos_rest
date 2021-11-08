package um.edu.uy.business;

import org.springframework.stereotype.Component;
import um.edu.uy.business.dtos.ClientDTO;
import um.edu.uy.business.entities.Client;

@Component
public class ClientMapper {

    public Client toClient(ClientDTO clientDTO) {
         Client client = new Client();
         client.setId(clientDTO.getId());
         client.setName(clientDTO.getName());
         client.setAddress(clientDTO.getAddress());
         client.setDocument(clientDTO.getDocument());

         return client;
    }

    public ClientDTO toDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setDocument(client.getDocument());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setName(client.getName());
        clientDTO.setId(client.getId());

        return clientDTO;
    }

}
