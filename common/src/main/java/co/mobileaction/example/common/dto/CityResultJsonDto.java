package co.mobileaction.example.common.dto;

import co.mobileaction.example.common.enums.CityEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class CityResultJsonDto
{
    @JsonProperty("City")
    private CityEnum city;

    @JsonProperty("Results")
    private List<ResultJsonDto> results;
}

