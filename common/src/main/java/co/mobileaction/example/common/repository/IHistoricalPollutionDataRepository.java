package co.mobileaction.example.common.repository;

import co.mobileaction.example.common.enums.CityEnum;
import co.mobileaction.example.common.model.HistoricalPollutionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IHistoricalPollutionDataRepository extends JpaRepository<HistoricalPollutionData, Long>
{
     Optional<HistoricalPollutionData> findByCityAndLocalDate(CityEnum city, LocalDate localDate);

     void deleteAllByCityAndLocalDateBetween(CityEnum city, LocalDate startDate,LocalDate endDate);

}
