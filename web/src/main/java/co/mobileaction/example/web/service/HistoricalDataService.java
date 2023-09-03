package co.mobileaction.example.web.service;

import co.mobileaction.example.common.converterService.DtoConverter;
import co.mobileaction.example.common.dto.DailyPollutionDataDto;
import co.mobileaction.example.common.dto.JsonFormatResultDto;
import co.mobileaction.example.common.dto.JsonFormatTitleDto;
import co.mobileaction.example.common.enums.CityEnum;
import co.mobileaction.example.common.model.HistoricalPollutionData;
import co.mobileaction.example.common.repository.IHistoricalPollutionDataRepository;
import co.mobileaction.example.web.client.ICrawlerClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HistoricalDataService implements IHistoricalDataService
{

    private final IHistoricalPollutionDataRepository historicalPollutionDataRepository;
    private final ICrawlerClient crawlerClient;

    @Override
    public JsonFormatTitleDto getHistoricalPollutionData(CityEnum city, LocalDate startDate, LocalDate endDate)
    {
        log.info("Data is getting for city: {}, start date: {}, end date: {}", city, startDate, endDate);
        assert startDate != null;
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        JsonFormatTitleDto jsonFormatTitleDto = new JsonFormatTitleDto();
        jsonFormatTitleDto.setCity(city);
        List<JsonFormatResultDto> jsonFormatResultDtoList = new ArrayList<>();
        for (int i = 0; i < daysBetween; i++)
        {
            LocalDate localDate = startDate.plusDays(i);
            jsonFormatResultDtoList.add(getHistoricalPollutionData(city,localDate));
        }

        jsonFormatTitleDto.setResults(jsonFormatResultDtoList);
        return jsonFormatTitleDto;
    }

    private JsonFormatResultDto getHistoricalPollutionData(CityEnum city, LocalDate localDate)
    {

        Optional<HistoricalPollutionData> historicalPollutionData = historicalPollutionDataRepository.findByCityAndLocalDate(city, localDate);
        if (historicalPollutionData.isPresent())
        {
            log.info("Data already exists in the database");
           return new JsonFormatResultDto(historicalPollutionData.get());
        }
        else
        {
            DtoConverter dtoConverter = new DtoConverter();
            DailyPollutionDataDto dailyPollutionDataDto = crawlerClient.fetchHistoricalPollutionData(city,localDate);
            log.info("Data does not exist in the database. Fetching from API.");
            historicalPollutionDataRepository.save(dailyPollutionDataDto.toHistoricalPollutionData());
            log.info("Data fetched from API and saved to the database.");
            return dtoConverter.convertToJsonFormatResultDto(dailyPollutionDataDto);
        }
    }

}