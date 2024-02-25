package ma.montaxi.montaxiRestfulApi.exceptions;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;

public abstract class HandledException extends RuntimeException{
    protected HandledException(String errorMsg) {
        super(errorMsg);
    }
    public ResponseEntity<?> handle() {
        throw new NotYetImplementedException();
    }
}
