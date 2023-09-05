package co.mobileaction.example.common.dto;

import co.mobileaction.example.common.util.CategoryConverter;
import co.mobileaction.example.common.enums.CategoryEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto
{
    @JsonProperty("SO2")
    private CategoryEnum so2;
    @JsonProperty("CO")
    private CategoryEnum co;
    @JsonProperty("O3")
    private CategoryEnum o3;

    public CategoryDto(BigDecimal so2, BigDecimal co, BigDecimal o3)
    {
        this.so2 = CategoryConverter.classifyLevelSo2(so2);
        this.co = CategoryConverter.classifyLevelCo(co);
        this.o3 = CategoryConverter.classifyLevelO3(o3);
    }

    public CategoryDto()
    {

    }

}
