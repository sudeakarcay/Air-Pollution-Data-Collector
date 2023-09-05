package co.mobileaction.example.common.util;

import co.mobileaction.example.common.dto.*;

public class DtoConverter
{
    public static ResultJsonDto convertToResultJsonDto(DailyPollutionDataDto dailyPollutionDataDto)
    {
        ResultJsonDto resultJsonDto = new ResultJsonDto();
        CityResultJsonDto cityResultJsonDto = new CityResultJsonDto();
        resultJsonDto.setDate(dailyPollutionDataDto.getDate());
        resultJsonDto.setCategoryDto(CategoryConverter.categoryResult(dailyPollutionDataDto));
        cityResultJsonDto.setCity(dailyPollutionDataDto.getCity());
        return resultJsonDto;
    }
}
