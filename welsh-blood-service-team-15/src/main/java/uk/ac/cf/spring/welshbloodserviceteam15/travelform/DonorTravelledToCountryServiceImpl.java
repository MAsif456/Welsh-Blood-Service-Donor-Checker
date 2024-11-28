package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;

import java.util.List;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountryRepository;

@Service
public class DonorTravelledToCountryServiceImpl implements DonorTravelledToCountryService {

    @Autowired
    private DonorTravelledToCountryRepository repository;

    @Override
    public List<DonorTravelledToCountry> getAllDonorTravels() {
        return repository.findAll();
    }

    @Override
    public DonorTravelledToCountry saveDonorTravel(DonorTravelledToCountry donorTravel) {
        return repository.save(donorTravel);
    }
}
