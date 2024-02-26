package ma.montaxi.montaxiRestfulApi.dtos;

import lombok.*;
import ma.montaxi.montaxiRestfulApi.settings.constants.CarSeats;
import ma.montaxi.montaxiRestfulApi.settings.enums.CreationStatus;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class TripDto {
    private Long id;
    @NotNull
    private Long journeyId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @Min(CarSeats.MIN_CAPACITY)
    @Max(CarSeats.MAX_CAPACITY)
    private Integer freeSeatsLeft;
    private CreationStatus creationStatus;
}
