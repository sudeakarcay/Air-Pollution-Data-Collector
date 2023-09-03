package co.mobileaction.example.web.client;

import co.mobileaction.example.common.dto.*;
import co.mobileaction.example.common.enums.CityEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlerClient implements ICrawlerClient
{
    public static final String geocodingApiUrl = "http://api.openweathermap.org/geo/1.0/direct?";
    public static final String historicalApiUrl = "http://api.openweathermap.org/data/2.5/air_pollution/history?";
    public static final String apiKey = "65144df8865c555d8799702e0ded7a33";

    private final IHttpRequestExecutor httpRequestExecutor;

    @Override
    public CoordinateDto fetchCoordinates(CityEnum cityName)
    {
        String url = geocodingApiUrl + "q=" + cityName + "&limit=1&appid=" + apiKey;
        return httpRequestExecutor.executeGetRequest(url, CoordinateDto[].class)[0];
    }

    @Override
    public DailyPollutionDataDto fetchHistoricalPollutionData(CityEnum city, LocalDate localdate)
    {
        //get start date as the start of the day and end date as the end of the day (conversion of local date to epoch time)
        LocalDateTime startOfDay = localdate.atTime(LocalTime.MIN); //gets the start of the day as epoch time
        long epochStartDate= startOfDay.atZone(ZoneOffset.UTC).toEpochSecond();

        LocalDateTime endOfDay = localdate.atTime(LocalTime.MAX); //gets the end of the day as epoch time
        long epochEndDate = endOfDay.atZone(ZoneOffset.UTC).toEpochSecond();

        CoordinateDto coordinates = fetchCoordinates(city);
        if(coordinates == null)
        {
            throw new RuntimeException("An error occurred while retrieving coordinates");
        }

        double lat = coordinates.getLat();
        double lon = coordinates.getLon();

        String url = historicalApiUrl + "lat=" + lat + "&lon=" + lon + "&start=" + epochStartDate + "&end=" + epochEndDate + "&appid=" + apiKey;

        TotalOfHourlyDataDto totalOfHourlyDataDto = httpRequestExecutor.executeGetRequest(url, TotalOfHourlyDataDto.class);

        List<HourlyPollutionDataDto> hourlyPollutionDataDto = Objects.requireNonNull(totalOfHourlyDataDto.getList());
        int dataSize = hourlyPollutionDataDto.size();

        BigDecimal avgOfCO = hourlyPollutionDataDto.stream()
                .map(pollutionDataDto -> pollutionDataDto.getComponents().getCo())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(dataSize), 2, RoundingMode.HALF_UP);

        BigDecimal avgOfSO2 = hourlyPollutionDataDto.stream()
                .map(pollutionDataDto -> pollutionDataDto.getComponents().getSo2())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(dataSize), 2, RoundingMode.HALF_UP);

        BigDecimal avgOfO3 = hourlyPollutionDataDto.stream()
                .map(pollutionDataDto -> pollutionDataDto.getComponents().getO3())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(dataSize), 2, RoundingMode.HALF_UP);


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
}
