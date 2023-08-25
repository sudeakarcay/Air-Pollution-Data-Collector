package co.mobileaction.example.worker.service;

import co.mobileaction.example.common.dto.CoordinateDto;
import co.mobileaction.example.common.model.Coordinate;
import co.mobileaction.example.common.repository.ICoordinateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoordinateExecutorService implements ICoordinateExecutorService
{

    private final ICoordinateRepository coordinateRepository;
    @Override
    public void executeMessage(CoordinateDto coordinateDto)
    {
        coordinateRepository.save(convertFrom(coordinateDto));
    }

    private Coordinate convertFrom(CoordinateDto coordinateDto)
    {
        return Coordinate.builder()
                .cityName(coordinateDto.getName())
                .lat(coordinateDto.getLat())
                .lon(coordinateDto.getLon())
                .build();
    }
}
