package co.mobileaction.example.common.repository;

import co.mobileaction.example.common.model.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoordinateRepository extends JpaRepository<Coordinate, String>
{

}

