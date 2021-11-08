package um.edu.uy.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ClientAlreadyExists extends Exception {

    public ClientAlreadyExists(String message) {
        super(message);
    }

    public ClientAlreadyExists() {
    }
}
