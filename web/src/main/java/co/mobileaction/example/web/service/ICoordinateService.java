package co.mobileaction.example.web.service;

import co.mobileaction.example.common.dto.CoordinateDto;
import co.mobileaction.example.common.model.Coordinate;

public interface ICoordinateService
{
    CoordinateDto getCoordinatesByCityName(String cityName);
}
