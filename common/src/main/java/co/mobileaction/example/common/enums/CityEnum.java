package co.mobileaction.example.common.enums;

import lombok.Getter;

@Getter
public enum CityEnum
{
    London ("London"),
    Barcelona ("Barcelona"),
    Ankara ("Ankara"),
    Tokyo ("Tokyo"),
    Mumbai ("Mumbai"),
    ;

    private final String city;

    CityEnum(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return city;
    }
}
