package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.Country;
import dev.seratt.mailing_system_main.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
    @Autowired
    CountryRepository countryRepository;
    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country findById(int id){
        return countryRepository.findById(id).get();
    }
}
