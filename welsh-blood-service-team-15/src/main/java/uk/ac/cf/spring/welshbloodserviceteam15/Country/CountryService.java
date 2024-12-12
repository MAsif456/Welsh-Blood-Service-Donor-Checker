package uk.ac.cf.spring.welshbloodserviceteam15.Country;

import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    Country getCountryById(Integer countryId);
}
