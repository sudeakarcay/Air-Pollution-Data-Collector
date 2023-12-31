package co.mobileaction.example.common.dto;

import co.mobileaction.example.common.model.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinateDto
{
    private String name;
    private double lat;
    private double lon;

    public CoordinateDto(Coordinate coordinate)
    {
        this.name = coordinate.getCityName();
        this.lat = coordinate.getLat();
        this.lon = coordinate.getLon();
    }

    public CoordinateDto(double lat, double lon)
    {
        this.lat = lat;
        this.lon = lon;
    }
}
