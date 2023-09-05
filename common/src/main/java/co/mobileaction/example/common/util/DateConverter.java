package co.mobileaction.example.common.util;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class DateConverter
{
    public static DateRange<Long, Long> localDateToEpochTime(LocalDate localDate)
    {
        if (localDate == null)
        {
            throw new IllegalArgumentException("Parameter localDate cannot be null");
        }
        //get start date as the start of the day and end date as the end of the day (conversion of local date to epoch time)
        LocalDateTime startOfDay = localDate.atTime(LocalTime.MIN); //gets the start of the day as epoch time
        long epochStartDate = startOfDay.atZone(ZoneOffset.UTC).toEpochSecond();

        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX); //gets the end of the day as epoch time
        long epochEndDate = endOfDay.atZone(ZoneOffset.UTC).toEpochSecond();

        return new DateRange<>(epochStartDate, epochEndDate);
    }

    @Getter
    public static class DateRange<startDate, endDate>
    {
        private final startDate start;
        private final endDate end;

        public DateRange(startDate startDate, endDate endDate)
        {
            this.start = startDate;
            this.end = endDate;
        }

    }
}