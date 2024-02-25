package ma.montaxi.montaxiRestfulApi.exceptions;

import org.springframework.http.ResponseEntity;

public class NoSeatsAvailable extends HandledException{

    public NoSeatsAvailable(Long tripId) {
        super("No seats left on Trip [" + tripId + "]");
    }

    @Override
    public ResponseEntity<?> handle() {
        printStackTrace();
        return ResponseEntity.internalServerError().body(getMessage());
    }
}
