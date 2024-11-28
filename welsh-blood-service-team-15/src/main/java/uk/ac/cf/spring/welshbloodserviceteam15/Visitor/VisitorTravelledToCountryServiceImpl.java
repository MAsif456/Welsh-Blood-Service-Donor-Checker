package uk.ac.cf.spring.welshbloodserviceteam15.Visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorTravelledToCountryServiceImpl implements VisitorTravelledToCountryService {

    @Autowired
    private VisitorTravelledToCountryRepository visitorTravelledToCountryRepository;

    @Override
    public void saveVisitorTravel(VisitorTravelledToCountry visitorTravel) {
        visitorTravelledToCountryRepository.save(visitorTravel);
    }

    @Override
    public List<VisitorTravelledToCountry> getVisitorTravelHistoryByDonorId(Integer donorId) {
        return visitorTravelledToCountryRepository.findByDonor_DonorId(donorId);
    }
}
