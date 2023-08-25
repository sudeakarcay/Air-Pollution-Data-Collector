package co.mobileaction.example.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "coordinates")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coordinate
{

    @Id
    @Column(name = "cityname")
    private String cityName;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;
}
