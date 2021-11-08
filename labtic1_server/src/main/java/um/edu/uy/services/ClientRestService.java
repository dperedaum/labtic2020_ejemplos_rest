package um.edu.uy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import um.edu.uy.business.ClientMapper;
import um.edu.uy.business.ClientMgr;
import um.edu.uy.business.dtos.ClientDTO;
import um.edu.uy.business.entities.Client;
import um.edu.uy.business.exceptions.ClientAlreadyExists;
import um.edu.uy.business.exceptions.InvalidClientInformation;

@RestController
@RequestMapping("/clients")
public class ClientRestService {

    private ClientMapper clientMapper;

    private ClientMgr clientMgr;

    @Autowired
    public ClientRestService(ClientMapper clientMapper, ClientMgr clientMgr) {
        this.clientMapper = clientMapper;
        this.clientMgr = clientMgr;
    }

    @PostMapping
    public void createClient(@RequestBody ClientDTO clientDTO) throws InvalidClientInformation, ClientAlreadyExists {
         Client client = clientMapper.toClient(clientDTO);

        clientMgr.addClient(client);
    }

}
