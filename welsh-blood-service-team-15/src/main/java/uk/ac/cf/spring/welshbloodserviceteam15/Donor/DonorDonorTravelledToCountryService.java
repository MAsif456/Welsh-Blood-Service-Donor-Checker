package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

public interface DonorDonorTravelledToCountryService {
    void saveFromForm(int donorId, int countryId, int regionId, String Date);

    void saveFromForm(int donorId, int countryId, String Date);
}
