package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import java.util.List;

public interface DonorDonorTravelledToCountryService {
    void saveFromForm(int donorId, int countryId, int regionId, String Date);

    void saveFromForm(int donorId, int countryId, String Date);

    List<DonorTravelledToCountry> findByDonorId(int donorId);
}
