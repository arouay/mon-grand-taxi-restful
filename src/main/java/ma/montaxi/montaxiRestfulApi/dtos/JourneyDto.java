package ma.montaxi.montaxiRestfulApi.dtos;

import lombok.*;
import ma.montaxi.montaxiRestfulApi.settings.enums.CreationStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class JourneyDto {
    private Long id;
    private String departurePlace;
    private String arrivalPlace;
    private Long price;
    private CreationStatus creationStatus;

}
