package sobes.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sobes.entities.City;
import sobes.entities.Country;
import sobes.entities.CountryLanguage;
import sobes.repository.CityRepository;
import sobes.repository.CountryLanguageRepository;
import sobes.repository.CountryRepository;

@SpringBootApplication
@EnableJpaRepositories("sobes.repository")
@EntityScan("sobes.entities")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner dbDemo(CityRepository cityRepository,
                                    CountryRepository countryRepository,
                                    CountryLanguageRepository countryLanguageRepository){
        return (args)->{
            for (String str:
                    countryLanguageRepository.firstQuery()) {
                System.out.println(str);
            }

//            System.out.println(countryLanguageRepository.findById("ZWE"));
//            System.out.println(countryRepository.findById("ZWE"));
//            for (Country c:
//                    countryRepository.findAll()) {
//                System.out.println(c);
//            }
        };
    }
}
