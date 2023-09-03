package co.mobileaction.example.common.converterService;

import co.mobileaction.example.common.dto.*;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter
{
    public static JsonFormatTitleDto resultConverter(DailyPollutionDataDto dailyPollutionDataDto)
    {
        JsonFormatTitleDto resultDto = new JsonFormatTitleDto();
        List<JsonFormatResultDto> resultDtoList = new ArrayList<>();
        resultDto.setCity(dailyPollutionDataDto.getCity());

        JsonFormatResultDto jsonFormatResultDto = new JsonFormatResultDto();
        jsonFormatResultDto.setDate(dailyPollutionDataDto.getDate());
        jsonFormatResultDto.setCategoryDto(CategoryConverter.categoryResult(dailyPollutionDataDto));
        resultDtoList.add(jsonFormatResultDto);

        resultDto.setResults(resultDtoList);
        return resultDto;
    }

    public JsonFormatResultDto convertToJsonFormatResultDto(DailyPollutionDataDto dailyPollutionDataDto)
    {
        JsonFormatResultDto jsonFormatResultDto = new JsonFormatResultDto();
        JsonFormatTitleDto jsonFormatTitleDto = new JsonFormatTitleDto();
        jsonFormatResultDto.setDate(dailyPollutionDataDto.getDate());
        jsonFormatResultDto.setCategoryDto(CategoryConverter.categoryResult(dailyPollutionDataDto));
        jsonFormatTitleDto.setCity(dailyPollutionDataDto.getCity());
        return jsonFormatResultDto;
    }
}
