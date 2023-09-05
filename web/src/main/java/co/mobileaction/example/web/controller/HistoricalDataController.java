package co.mobileaction.example.web.controller;

import co.mobileaction.example.common.dto.CityResultJsonDto;
import co.mobileaction.example.common.enums.CityEnum;
import co.mobileaction.example.common.repository.IHistoricalPollutionDataRepository;
import co.mobileaction.example.web.service.IHistoricalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/historical/data")
@RequiredArgsConstructor
public class HistoricalDataController
{
    private final IHistoricalDataService historicalDataService;
    private final IHistoricalPollutionDataRepository historicalPollutionDataRepository;

    @GetMapping
    public ResponseEntity<CityResultJsonDto> getHistoricalPollutionData(@RequestParam(value = "city") CityEnum city,
                                                                        @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
                                                                        @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate)
    {
        if (startDate == null && endDate == null)
        {
            startDate = LocalDate.now().minusDays(7);
            endDate = LocalDate.now();
        }
        else
        {
            LocalDate minHistoricalDate = LocalDate.of(2020, 11, 27);
            LocalDate currentDate = LocalDate.now();

            assert startDate != null;
            if (startDate.isBefore(minHistoricalDate))
            {
                throw new IllegalArgumentException("The historical data access has been limited from November 27, 2020, to the present day.");
            }

            if (endDate.isBefore(startDate))
            {
                throw new IllegalArgumentException("The end date cannot be before the start date");
            }

            if (startDate.isAfter(currentDate))
            {
                throw new IllegalArgumentException("The dates cannot be after today");
            }
        }

        CityResultJsonDto cityResultJsonDto = historicalDataService.getHistoricalPollutionData(city, startDate, endDate);
        return ResponseEntity.ok(cityResultJsonDto);
    }

    @DeleteMapping
    public void deleteHistoricalPollutionData(@RequestParam(value = "city") CityEnum city,
                                              @RequestParam(value = "startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
                                              @RequestParam(value = "endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate)
    {
        historicalPollutionDataRepository.deleteAllByCityAndLocalDateBetween(city, startDate, endDate);
    }
}
