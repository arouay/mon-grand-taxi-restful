package ma.montaxi.montaxiRestfulApi.dtos;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class LocationDto {
    private Double longitude;
    private Double latitude;
}
