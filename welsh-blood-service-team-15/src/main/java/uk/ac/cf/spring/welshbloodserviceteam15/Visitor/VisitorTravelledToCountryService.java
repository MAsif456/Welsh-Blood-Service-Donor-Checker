package uk.ac.cf.spring.welshbloodserviceteam15.Visitor;

import java.util.List;

public interface VisitorTravelledToCountryService {
    void saveVisitorTravel(VisitorTravelledToCountry visitorTravel);
    List<VisitorTravelledToCountry> getVisitorTravelHistoryByDonorId(Integer donorId);
}
