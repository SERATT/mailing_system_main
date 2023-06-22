package dev.seratt.mailing_system_main.repository;

import dev.seratt.mailing_system_main.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository  extends JpaRepository<Country, Integer> {
}
