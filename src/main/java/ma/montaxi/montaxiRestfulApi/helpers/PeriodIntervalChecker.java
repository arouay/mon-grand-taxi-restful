package ma.montaxi.montaxiRestfulApi.helpers;

import java.time.LocalDateTime;

public class PeriodIntervalChecker {
    public static Boolean isIntervalsOverlapped(LocalDateTime departureTime1,
                                                LocalDateTime arrivalTime1,
                                                LocalDateTime departureTime2,
                                                LocalDateTime arrivalTime2) {
        if (departureTime1.isEqual(departureTime2)
            || arrivalTime1.isEqual(arrivalTime2)) {
            return true;
        }

        Boolean intervalIncludesInterval = (departureTime1.isBefore(departureTime2) && arrivalTime1.isAfter(arrivalTime2))
                                            || (departureTime1.isAfter(departureTime2) && arrivalTime1.isBefore(arrivalTime2)),
                intervalsLeftOverlapped = departureTime1.isAfter(departureTime2) && arrivalTime1.isAfter(arrivalTime2) && departureTime1.isBefore(arrivalTime2),
                intervalsRightOverlapped = departureTime1.isBefore(departureTime2) && arrivalTime1.isBefore(arrivalTime2) && departureTime2.isBefore(arrivalTime1);

        return intervalIncludesInterval || intervalsLeftOverlapped || intervalsRightOverlapped;
    }
}
