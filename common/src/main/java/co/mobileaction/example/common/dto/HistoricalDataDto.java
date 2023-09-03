package co.mobileaction.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricalDataDto
{
 private LocalDate date;
 private double so2;
 private double co;
 private double o3;
}
