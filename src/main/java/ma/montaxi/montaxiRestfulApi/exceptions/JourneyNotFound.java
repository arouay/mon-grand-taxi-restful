package ma.montaxi.montaxiRestfulApi.exceptions;

import org.springframework.http.ResponseEntity;

public class JourneyNotFound extends HandledException{
    public JourneyNotFound(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public ResponseEntity<?> handle() {
        printStackTrace();
        return ResponseEntity.internalServerError().body(getMessage());
    }
}
