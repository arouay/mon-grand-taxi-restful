package ma.montaxi.montaxiRestfulApi.dtos;

import lombok.Builder;
import lombok.Data;
import ma.montaxi.montaxiRestfulApi.settings.enums.CreationStatus;

@Builder
@Data
public class JourneyDto {
    private Long id;
    private String departurePlace;
    private String arrivalPlace;
    private Long price;
    private CreationStatus creationStatus;

}
