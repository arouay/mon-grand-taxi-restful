package ma.montaxi.montaxiRestfulApi.exceptions;

import org.springframework.http.ResponseEntity;

public class InvalidUserException extends HandledException{
    public InvalidUserException(String errorMessage) {
        super(errorMessage);
    }
    @Override
    public ResponseEntity<?> handle() {
        printStackTrace();
        return ResponseEntity.internalServerError().body(getMessage());
    }
}
