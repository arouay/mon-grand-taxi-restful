package ma.montaxi.montaxiRestfulApi.settings.enums;

public enum ReservationStatus {
    NEW, // passenger created reservation but not yet detected on trip's journey
    CREATED, // status updated automatically after checking the user is indeed waiting
    ONBOARD,
    ARRIVED,
    CANCELED;

    ReservationStatus() {
    }
}
