package co.mobileaction.example.web.controller;

import co.mobileaction.example.common.dto.JsonFormatTitleDto;
import co.mobileaction.example.common.enums.CityEnum;
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

    @GetMapping
    public ResponseEntity<JsonFormatTitleDto> getHistoricalPollutionData(@RequestParam (value = "city") CityEnum city,
                                                                            @RequestParam(value = "startDate" , required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
                                                                            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate)
    {
        if (startDate == null && endDate == null) {
            startDate = LocalDate.now().minusDays(7);
            endDate = LocalDate.now();
        } else {
            LocalDate minHistoricalDate = LocalDate.of(2020, 11, 27);
            LocalDate currentDate = LocalDate.now();

            assert startDate != null;
            if (startDate.isBefore(minHistoricalDate)) {
                throw new IllegalArgumentException("The historical data access has been limited from November 27, 2020, to the present day.");
            }

            if (endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("The end date cannot be before the start date");
            }

            if (startDate.isAfter(currentDate) && endDate.isAfter(currentDate)) {
                throw new IllegalArgumentException("The start date and the end date cannot be after today");
            }
        }

        JsonFormatTitleDto jsonFormatTitleDto = historicalDataService.getHistoricalPollutionData(city, startDate, endDate);
        return ResponseEntity.ok(jsonFormatTitleDto);
    }
}
