package co.mobileaction.example.web.client;


import co.mobileaction.example.common.dto.CoordinateDto;


public interface ICrawlerClient
{
    CoordinateDto fetchCoordinates(String cityName);
}
