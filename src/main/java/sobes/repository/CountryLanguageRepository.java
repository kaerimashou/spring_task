package sobes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sobes.entities.CountryLanguage;

import java.util.List;
import java.util.Optional;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage,String> {
    @Query(value = "SELECT * FROM countrylanguage WHERE CountryCode=?1",nativeQuery = true)
    List<CountryLanguage> findAllById(String CountryCode);

    @Query(value = "SELECT language FROM countrylanguage cl",nativeQuery = true)
    List<String> firstQuery();

}
