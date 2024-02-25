package ma.montaxi.montaxiRestfulApi.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ReservationDto {
    private Long tripId;
    private LocalDateTime reservedAt;
}
