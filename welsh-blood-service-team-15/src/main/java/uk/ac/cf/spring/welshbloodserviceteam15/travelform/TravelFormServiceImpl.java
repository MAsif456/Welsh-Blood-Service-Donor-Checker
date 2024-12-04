package uk.ac.cf.spring.welshbloodserviceteam15.travelform;

import org.springframework.stereotype.Service;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Region.Region;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.Donor;
import uk.ac.cf.spring.welshbloodserviceteam15.Donor.DonorTravelledToCountry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TravelFormServiceImpl implements TravelFormService{

    private final DonorTravelledToCountryService donorTravelledToCountryService;

    public TravelFormServiceImpl(DonorTravelledToCountryService donorTravelledToCountryService) {
        this.donorTravelledToCountryService = donorTravelledToCountryService;
    }

    public void saveDonorTravel(Map<String, String> formData, Long donorId, String countryKeyPrefix, String endDateKeyPrefix, String regionKeyPrefix) {
        List<DonorTravelledToCountry> travelHistory = new ArrayList<>();

        formData.forEach((key, value) -> {
            if (key.startsWith(countryKeyPrefix)) {
                String index = key.substring(countryKeyPrefix.length());
                String endDateStr = formData.get(endDateKeyPrefix + index);
                String regionIdStr = formData.get(regionKeyPrefix + index);

                if (value != null && endDateStr != null) {
                    DonorTravelledToCountry donorTravel = new DonorTravelledToCountry();
                    Donor donor = new Donor();
                    donor.setDonorId(donorId.intValue()); // Convert Long to Integer
                    donorTravel.setDonor(donor);
                    Country country = new Country();
                    country.setCountryId(Integer.parseInt(value));
                    donorTravel.setCountry(country);

                    // Set region to null if not present, else parse and set it
                    if (regionIdStr != null && !regionIdStr.isEmpty()) {
                        Region region = new Region();
                        region.setRegionId(Integer.parseInt(regionIdStr));
                        donorTravel.setRegion(region);
                    } else {
                        donorTravel.setRegion(null); // Explicitly set region to null if not provided
                    }

                    donorTravel.setEndDate(LocalDate.parse(endDateStr));
                    travelHistory.add(donorTravel);
                }
            }
        });

        travelHistory.forEach(donorTravelledToCountryService::saveDonorTravel);
    }
}
