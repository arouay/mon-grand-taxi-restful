package ma.montaxi.montaxiRestfulApi.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationDto {
    private Double longitude;
    private Double latitude;
}
