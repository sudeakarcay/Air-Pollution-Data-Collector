package co.mobileaction.example.web.client;


import co.mobileaction.example.common.dto.CoordinateDto;

import java.util.List;

public interface ICrawlerClient
{
    CoordinateDto fetchCoordinates(String cityName);
}
