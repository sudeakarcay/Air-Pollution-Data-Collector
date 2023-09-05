package co.mobileaction.example.common.enums;

import lombok.Getter;

@Getter
public enum CategoryEnum
{
    Good("Good"),
    Satisfactory("Satisfactory"),
    Moderate("Moderate"),
    Poor("Poor"),
    Severe("Severe"),
    Hazardous("Hazardous");

    private final String category;

    CategoryEnum(String category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return category;
    }
}
