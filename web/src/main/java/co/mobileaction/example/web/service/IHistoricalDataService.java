package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.JsonFormatTitleDto;
import co.mobileaction.example.common.enums.CityEnum;

import java.time.LocalDate;

public interface IHistoricalDataService
{
    JsonFormatTitleDto getHistoricalPollutionData(CityEnum city, LocalDate startDate, LocalDate endDate);

}
