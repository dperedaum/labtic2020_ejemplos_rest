package um.edu.uy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import um.edu.uy.business.dtos.ClientDTO;
import org.springframework.http.ResponseEntity;

@Component
public class ClientRestService {

    private RestTemplate restTemplate;

    @Autowired
    private ClientRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity addClient(ClientDTO client) {
        return restTemplate.postForEntity("http://localhost:8080/clients",
                client, ClientDTO.class);
    }

}
