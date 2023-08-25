package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.CoordinateDto;

public interface ICoordinateService
{
    CoordinateDto getCoordinatesByCityName(String cityName);
}
