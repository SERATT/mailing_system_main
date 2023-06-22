package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.City;
import dev.seratt.mailing_system_main.entity.Country;
import dev.seratt.mailing_system_main.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CityServiceImpl implements CityService{
    @Autowired
    CityRepository cityRepository;

    public Set<City> getAllCitiesByCountry(Country country){
        return cityRepository.findCitiesByCountry(country);
    }
    public City findById(int id){
        return cityRepository.findById(id).get();
    }
}
