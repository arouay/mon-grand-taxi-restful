package ma.montaxi.montaxiRestfulApi.exceptions;

import org.springframework.http.ResponseEntity;

public class OutdatedTripException extends HandledException{

    public OutdatedTripException(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public ResponseEntity<?> handle() {
        printStackTrace();
        return ResponseEntity.internalServerError().body(getMessage());
    }
}
