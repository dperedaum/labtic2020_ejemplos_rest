package um.edu.uy.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidClientInformation extends Exception {

    public InvalidClientInformation(String message) {
        super(message);
    }
}
