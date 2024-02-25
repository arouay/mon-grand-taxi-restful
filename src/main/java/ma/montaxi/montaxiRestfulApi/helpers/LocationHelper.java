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
        final int R = 6371; // Radius of the earth

        var lat1 = location1.getLatitude();
        var lat2 = location2.getLatitude();

        var lon1 = location1.getLongitude();
        var lon2 = location2.getLongitude();

        // End altitude in meters
        var el1 = 0;
        var el2 = 0;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
