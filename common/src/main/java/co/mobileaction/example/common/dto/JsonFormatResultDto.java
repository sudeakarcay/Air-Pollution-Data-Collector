package co.mobileaction.example.common.dto;

import co.mobileaction.example.common.model.HistoricalPollutionData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class JsonFormatResultDto
{
    @JsonProperty("Date")
    private String date;

    @JsonProperty("Categories")
    private CategoryDto categoryDto;

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public JsonFormatResultDto (HistoricalPollutionData historicalPollutionData)
    {
        setDate(historicalPollutionData.getLocalDate());
        this.categoryDto = new CategoryDto(historicalPollutionData.getSo2(),historicalPollutionData.getCo(),historicalPollutionData.getO3());

    }
    public void setDate(LocalDate date)
    {
        this.date = formatter.format(date);
    }
}
