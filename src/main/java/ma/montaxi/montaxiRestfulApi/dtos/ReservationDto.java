package ma.montaxi.montaxiRestfulApi.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class ReservationDto {
    private Long tripId;
    private LocalDateTime reservedAt;
}
