package co.mobileaction.example.web.client;


import co.mobileaction.example.common.dto.CoordinateDto;
import co.mobileaction.example.common.dto.DailyPollutionDataDto;
import co.mobileaction.example.common.enums.CityEnum;

import java.time.LocalDate;


public interface ICrawlerClient
{
    CoordinateDto fetchCoordinates(CityEnum cityName);

    DailyPollutionDataDto fetchHistoricalPollutionData(CityEnum city, LocalDate startDate);
}
