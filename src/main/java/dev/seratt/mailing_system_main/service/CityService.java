package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.City;
import dev.seratt.mailing_system_main.entity.Country;

import java.util.Set;

public interface CityService {
    public Set<City> getAllCitiesByCountry(Country country);
    public City findById(int id);

}
