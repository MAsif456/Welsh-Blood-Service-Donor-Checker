package uk.ac.cf.spring.welshbloodserviceteam15.Donor;

import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;

import java.util.List;

public interface DonorTravelledToCountryService {
    List<DonorTravelledToCountry> getAllDonorTravels();
    DonorTravelledToCountry saveDonorTravel(DonorTravelledToCountry donorTravel);
}