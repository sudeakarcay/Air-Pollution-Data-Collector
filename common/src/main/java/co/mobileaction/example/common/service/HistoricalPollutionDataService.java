package co.mobileaction.example.common.service;

import co.mobileaction.example.common.dto.JsonFormatTitleDto;
import co.mobileaction.example.common.model.HistoricalPollutionData;
import co.mobileaction.example.common.repository.IHistoricalPollutionDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static co.mobileaction.example.common.dto.JsonFormatResultDto.formatter;

@Service
@RequiredArgsConstructor
public class HistoricalPollutionDataService implements IHistoricalPollutionDataService
{

    private final IHistoricalPollutionDataRepository historicalPollutionDataRepository;
    @Override
    public void save(JsonFormatTitleDto jsonFormatTitleDto)
    {
        historicalPollutionDataRepository.saveAll(convertFrom(jsonFormatTitleDto));
    }


    private List<HistoricalPollutionData> convertFrom(JsonFormatTitleDto jsonFormatTitleDto)
    {
        List<HistoricalPollutionData> pollutionData = new ArrayList<>();
        jsonFormatTitleDto.getResults().forEach(dto -> {
            final HistoricalPollutionData historicalPollutionData = HistoricalPollutionData.builder()
                    .city(jsonFormatTitleDto.getCity())
                    .localDate(LocalDate.parse(dto.getDate(), formatter))
                    .so2(dto.getCategoryDto().getSo2())
                    .co(dto.getCategoryDto().getCo())
                    .o3(dto.getCategoryDto().getO3())
                    .build();
            pollutionData.add(historicalPollutionData);
        });
        return pollutionData;
    }
}
