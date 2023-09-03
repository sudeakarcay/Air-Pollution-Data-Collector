package co.mobileaction.example.web.service;

import co.mobileaction.example.common.enums.CityEnum;
import co.mobileaction.example.web.client.ICrawlerClient;
import co.mobileaction.example.common.dto.CoordinateDto;
import co.mobileaction.example.common.model.Coordinate;
import co.mobileaction.example.common.repository.ICoordinateRepository;
import co.mobileaction.example.web.queue.ICoordinateQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoordinateService implements ICoordinateService
{
    private final ICrawlerClient crawlerClient;
    private final ICoordinateRepository coordinateRepository;
    private final ICoordinateQueueService coordinateQueueService;

    @Override
    public CoordinateDto getCoordinatesByCityName(CityEnum cityName)
    {
        Optional<Coordinate> coordinate = coordinateRepository.findById(String.valueOf(cityName));
        if (coordinate.isPresent())
        {
            return new CoordinateDto(coordinate.get());
        }
        else
        {
            // first queue then return
            CoordinateDto coordinateDto = crawlerClient.fetchCoordinates(cityName);
            coordinateQueueService.queueRequestForCoordinates(coordinateDto);
            return coordinateDto;
        }
    }
}
