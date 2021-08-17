package sobes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sobes.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
