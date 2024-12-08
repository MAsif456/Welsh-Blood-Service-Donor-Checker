package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import java.util.Map;

public interface TravelFormService {

    void saveDonorTravel(Map<String, String> formData, Long donorId, String countryKeyPrefix, String endDateKeyPrefix, String regionKeyPrefix);

    void saveDonorTravelRegion(Map<String, String> formData, Long donorId, String countryKeyPrefix, String endDateKeyPrefix, long regionId);
}
