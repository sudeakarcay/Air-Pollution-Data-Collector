package co.mobileaction.example.web.client;

import co.mobileaction.example.common.util.PollutionDataCalculator;
import co.mobileaction.example.common.util.DateConverter;
import co.mobileaction.example.common.dto.*;
import co.mobileaction.example.common.enums.CityEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlerClient implements ICrawlerClient
{
    private static final String GEOCODING_API_URL = "http://api.openweathermap.org/geo/1.0/direct?";
    private static final String HISTORICAL_API_URL = "http://api.openweathermap.org/data/2.5/air_pollution/history?";
    private static final String API_KEY = "65144df8865c555d8799702e0ded7a33";

    private final IHttpRequestExecutor httpRequestExecutor;

    @Override
    public CoordinateDto fetchCoordinates(CityEnum cityName)
    {
        String url = String.format("%sq=%s&limit=1&appid=%s", GEOCODING_API_URL, cityName, API_KEY);
        return httpRequestExecutor.executeGetRequest(url, CoordinateDto[].class)[0];
    }

    @Override
    public DailyPollutionDataDto fetchHistoricalPollutionData(CityEnum city, LocalDate localdate)
    {
        CoordinateDto coordinates = fetchCoordinates(city);
        validateCoordinates(coordinates);

        double lat = coordinates.getLat();
        double lon = coordinates.getLon();

        DateConverter.DateRange<Long, Long> epochDates = DateConverter.localDateToEpochTime(localdate);
        String url = String.format(
                "%slat=%s&lon=%s&start=%s&end=%s&appid=%s",
                HISTORICAL_API_URL, lat, lon, epochDates.getStart(), epochDates.getEnd(), API_KEY
        );

        TotalOfHourlyDataDto totalOfHourlyDataDto = httpRequestExecutor.executeGetRequest(url, TotalOfHourlyDataDto.class);

        List<HourlyPollutionDataDto> hourlyPollutionDataDto = Objects.requireNonNull(totalOfHourlyDataDto.getList());

        BigDecimal avgOfCO = PollutionDataCalculator.calculateAverage(hourlyPollutionDataDto, dto -> dto.getComponents().getCo());
        BigDecimal avgOfSO2 = PollutionDataCalculator.calculateAverage(hourlyPollutionDataDto, dto -> dto.getComponents().getSo2());
        BigDecimal avgOfO3 = PollutionDataCalculator.calculateAverage(hourlyPollutionDataDto, dto -> dto.getComponents().getO3());

        PollutionTypesDto pollutionTypesDto = new PollutionTypesDto();
        pollutionTypesDto.setCo(avgOfCO);
        pollutionTypesDto.setSo2(avgOfSO2);
        pollutionTypesDto.setO3(avgOfO3);

        return DailyPollutionDataDto.builder()
                .city(city)
                .date(localdate)
                .pollutionTypes(pollutionTypesDto)
                .build();
    }

    private void validateCoordinates(CoordinateDto coordinates)
    {
        if (coordinates == null)
        {
            throw new CoordinatesNotFoundException("Coordinates not found for the city.");
        }
    }

    // Define custom exception class for error handling
    public static class CoordinatesNotFoundException extends RuntimeException
    {
        public CoordinatesNotFoundException(String message)
        {
            super(message);
        }
    }
}
