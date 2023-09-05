package co.mobileaction.example.common.service;

import co.mobileaction.example.common.dto.CityResultJsonDto;
import co.mobileaction.example.common.model.HistoricalPollutionData;
import co.mobileaction.example.common.repository.IHistoricalPollutionDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static co.mobileaction.example.common.dto.ResultJsonDto.formatter;

@Service
@RequiredArgsConstructor
public class HistoricalPollutionDataService implements IHistoricalPollutionDataService
{

    private final IHistoricalPollutionDataRepository historicalPollutionDataRepository;
    @Override
    public void save(CityResultJsonDto cityResultJsonDto)
    {
        historicalPollutionDataRepository.saveAll(convertFrom(cityResultJsonDto));
    }


    private List<HistoricalPollutionData> convertFrom(CityResultJsonDto cityResultJsonDto)
    {
        List<HistoricalPollutionData> pollutionData = new ArrayList<>();
        cityResultJsonDto.getResults().forEach(dto -> {
            final HistoricalPollutionData historicalPollutionData = HistoricalPollutionData.builder()
                    .city(cityResultJsonDto.getCity())
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
