package dev.seratt.mailing_system_main.repository;

import dev.seratt.mailing_system_main.entity.City;
import dev.seratt.mailing_system_main.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CityRepository extends JpaRepository<City, Integer> {
    public Set<City> findCitiesByCountry(Country country);
}
