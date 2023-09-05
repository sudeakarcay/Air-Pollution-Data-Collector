package co.mobileaction.example.common.util;

import co.mobileaction.example.common.dto.HourlyPollutionDataDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;

public class PollutionDataCalculator
{
    public static BigDecimal calculateAverage(
            List<HourlyPollutionDataDto> data,
            Function<HourlyPollutionDataDto, BigDecimal> componentExtractor
    )
    {
        try
        {
            BigDecimal sum = data.stream()
                    .map(componentExtractor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            int dataSize = data.size();
            if (dataSize == 0)
            {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            return sum.divide(BigDecimal.valueOf(dataSize), 2, RoundingMode.HALF_UP);
        }
        catch (ArithmeticException e)
        {
            throw new RuntimeException("An error occurred during calculation", e);

        }
    }
}
