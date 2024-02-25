package ma.montaxi.montaxiRestfulApi.exceptions;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public class IncorrectMomentException extends HandledException {
    public IncorrectMomentException(LocalDateTime ...moments) {
        super("Incorrect moment provided " +
                "[" +
                    Arrays.stream(moments)
                            .map(LocalDateTime::toString)
                            .collect(Collectors.joining(", ")) +
                "]");
    }

    @Override
    public ResponseEntity<?> handle() {
        printStackTrace();
        return ResponseEntity.internalServerError().body(getMessage());
    }
}
