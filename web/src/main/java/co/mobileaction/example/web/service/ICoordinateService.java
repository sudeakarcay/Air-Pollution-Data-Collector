package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.CoordinateDto;
import co.mobileaction.example.common.enums.CityEnum;

public interface ICoordinateService
{
    CoordinateDto getCoordinatesByCityName(CityEnum cityName);
}
