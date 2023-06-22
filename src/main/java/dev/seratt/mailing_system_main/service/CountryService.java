package dev.seratt.mailing_system_main.service;

import dev.seratt.mailing_system_main.entity.Country;

import java.util.List;

public interface CountryService {
    public List<Country> getAllCountries();

    public Country findById(int id);
}
