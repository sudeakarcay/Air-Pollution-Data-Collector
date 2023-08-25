package co.mobileaction.example.web.client;

import co.mobileaction.example.common.dto.CoordinateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlerClient implements ICrawlerClient
{
    public static final String geocodingApiUrl = "http://api.openweathermap.org/geo/1.0/direct?";
    private final IHttpRequestExecutor httpRequestExecutor;

    @Override
    public CoordinateDto fetchCoordinates(String cityName)
    {
        String apiKey = "65144df8865c555d8799702e0ded7a33";
        String url = geocodingApiUrl + "q=" + cityName + "&limit=1&appid=" + apiKey;

        return httpRequestExecutor.executeGetRequest(url, CoordinateDto[].class)[0];
    }

}
