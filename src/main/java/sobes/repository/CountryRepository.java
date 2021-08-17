package sobes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sobes.entities.Country;

public interface CountryRepository extends JpaRepository<Country,String> {
}
