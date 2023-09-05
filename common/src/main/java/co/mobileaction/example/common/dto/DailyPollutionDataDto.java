package co.mobileaction.example.common.dto;

import co.mobileaction.example.common.enums.CityEnum;
import co.mobileaction.example.common.model.HistoricalPollutionData;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class DailyPollutionDataDto
{
    private CityEnum city;
    private PollutionTypesDto pollutionTypes;
    private LocalDate date;

    public HistoricalPollutionData toHistoricalPollutionData()
    {
        CategoryDto categoryDto = new CategoryDto(pollutionTypes.getSo2(), pollutionTypes.getCo(), pollutionTypes.getO3());
        return HistoricalPollutionData.builder()
                .city(city)
                .localDate(date)
                .so2(categoryDto.getSo2())
                .co(categoryDto.getCo())
                .o3(categoryDto.getO3())
                .build();
    }
}
