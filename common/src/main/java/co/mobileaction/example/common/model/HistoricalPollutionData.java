package co.mobileaction.example.common.model;

import co.mobileaction.example.common.enums.CategoryEnum;
import co.mobileaction.example.common.enums.CityEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "historical_pollution")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricalPollutionData
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private CityEnum city;

    @Column(name = "date")
    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "so2")
    private CategoryEnum so2;

    @Enumerated(EnumType.STRING)
    @Column(name = "co")
    private CategoryEnum co;

    @Enumerated(EnumType.STRING)
    @Column(name = "o3")
    private CategoryEnum o3;

}
