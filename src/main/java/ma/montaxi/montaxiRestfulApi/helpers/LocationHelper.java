package ma.montaxi.montaxiRestfulApi.helpers;

import ma.montaxi.montaxiRestfulApi.dtos.LocationDto;
import ma.montaxi.montaxiRestfulApi.entities.Journey;

public class LocationHelper {
    /**
     * get the distance between two locations in a journey provided as parameters
     * if the location1 is ahead of location2 the distance will be a negative Double number
     * @param location1
     * @param location2
     * @param journey
     * @return
     */
    public static Double getDistanceBetweenTwoLocationsInJourney(LocationDto location1, LocationDto location2, Journey journey) {
        // TODO should implement google API to get distances

        return 1.1; // Only As Example
    }
}
