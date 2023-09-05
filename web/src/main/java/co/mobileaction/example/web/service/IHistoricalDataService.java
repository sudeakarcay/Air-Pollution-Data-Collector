package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.CityResultJsonDto;
import co.mobileaction.example.common.enums.CityEnum;

import java.time.LocalDate;

public interface IHistoricalDataService
{
    CityResultJsonDto getHistoricalPollutionData(CityEnum city, LocalDate startDate, LocalDate endDate);
}
