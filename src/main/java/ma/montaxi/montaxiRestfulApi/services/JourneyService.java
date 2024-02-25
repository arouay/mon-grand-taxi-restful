package ma.montaxi.montaxiRestfulApi.services;

import lombok.extern.slf4j.Slf4j;
import ma.montaxi.montaxiRestfulApi.daos.IJourneyRepository;
import ma.montaxi.montaxiRestfulApi.dtos.JourneyDto;
import ma.montaxi.montaxiRestfulApi.entities.Journey;
import ma.montaxi.montaxiRestfulApi.settings.enums.CreationStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class JourneyService {
    private final IJourneyRepository journeyRepository;

    public JourneyService(IJourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public JourneyDto saveJourney (Journey journey) {
        JourneyDto.JourneyDtoBuilder journeyDto = JourneyDto.builder()
                .departurePlace(journey.getDeparturePlace())
                .arrivalPlace(journey.getArrivalPlace())
                .price(journey.getPrice());
        if (this.journeyRepository.existsByDeparturePlaceAndArrivalPlace(journey.getDeparturePlace(), journey.getArrivalPlace())) {
            log.info("****** Journey with same departurePlace and arrivalPlace already exist ******");
            journeyDto.creationStatus(CreationStatus.ALREADY_EXIST);
        } else {
            var createdJourney = this.journeyRepository.save(journey);
            journeyDto.id(createdJourney.getId());
            journeyDto.creationStatus(CreationStatus.CREATED);
        }

        return journeyDto.build();
    }
}
