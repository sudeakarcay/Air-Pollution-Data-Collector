package co.mobileaction.example.common.util;

import co.mobileaction.example.common.dto.CategoryDto;
import co.mobileaction.example.common.dto.DailyPollutionDataDto;
import co.mobileaction.example.common.enums.CategoryEnum;

import java.math.BigDecimal;

public class CategoryConverter
{
    public static CategoryDto categoryResult(DailyPollutionDataDto dailyPollutionDataDto)
    {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setSo2(classifyLevelSo2(dailyPollutionDataDto.getPollutionTypes().getSo2()));
        categoryDto.setCo(classifyLevelCo(dailyPollutionDataDto.getPollutionTypes().getCo()));
        categoryDto.setO3(classifyLevelO3(dailyPollutionDataDto.getPollutionTypes().getO3()));
        return categoryDto;
    }

    public static CategoryEnum classifyLevelCo(BigDecimal concentrationCO)
    {
        if (concentrationCO.doubleValue() <= 1.0)
        {
            return CategoryEnum.Good;
        }
        else if (concentrationCO.doubleValue() <= 2.0)
        {
            return CategoryEnum.Satisfactory;
        }
        else if (concentrationCO.doubleValue() <= 10.0)
        {
            return CategoryEnum.Moderate;
        }
        else if (concentrationCO.doubleValue() <= 17.0)
        {
            return CategoryEnum.Poor;
        }
        else if (concentrationCO.doubleValue() <= 34.0)
        {
            return CategoryEnum.Severe;
        }
        else
        {
            return CategoryEnum.Hazardous;
        }
    }

    public static CategoryEnum classifyLevelSo2(BigDecimal concentrationSO2)
    {
        if (concentrationSO2.doubleValue() <= 40)
        {
            return CategoryEnum.Good;
        }
        else if (concentrationSO2.doubleValue() <= 80)
        {
            return CategoryEnum.Satisfactory;
        }
        else if (concentrationSO2.doubleValue() <= 380)
        {
            return CategoryEnum.Moderate;
        }
        else if (concentrationSO2.doubleValue() <= 800)
        {
            return CategoryEnum.Poor;
        }
        else if (concentrationSO2.doubleValue() <= 1600)
        {
            return CategoryEnum.Severe;
        }
        else
        {
            return CategoryEnum.Hazardous;
        }
    }

    public static CategoryEnum classifyLevelO3(BigDecimal concentrationO3)
    {
        if (concentrationO3.doubleValue() <= 50)
        {
            return CategoryEnum.Good;
        }
        else if (concentrationO3.doubleValue() <= 100)
        {
            return CategoryEnum.Satisfactory;
        }
        else if (concentrationO3.doubleValue() <= 200)
        {
            return CategoryEnum.Moderate;
        }
        else if (concentrationO3.doubleValue() <= 300)
        {
            return CategoryEnum.Poor;
        }
        else if (concentrationO3.doubleValue() <= 400)
        {
            return CategoryEnum.Severe;
        }
        else
        {
            return CategoryEnum.Hazardous;
        }
    }
}
