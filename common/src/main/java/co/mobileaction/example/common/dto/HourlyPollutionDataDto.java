package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HourlyPollutionDataDto
{
    private PollutionTypesDto components;
    private Long dt;
}
