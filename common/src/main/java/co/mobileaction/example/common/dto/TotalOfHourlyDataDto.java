package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalOfHourlyDataDto
{
    private List<HourlyPollutionDataDto> list;
}


//move hourly pollution data dto here