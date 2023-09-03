package co.mobileaction.example.common.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class PollutionTypesDto
{
    private BigDecimal so2;
    private BigDecimal co;
    private BigDecimal o3;
}
