package uk.ac.cf.spring.welshbloodserviceteam15.Country.Region;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/region")
public class RegionApiController {

    private final RegionService regionService;

    public RegionApiController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/get-regions")
    public ResponseEntity<List<Region>> getRegions() {
        List<Region> regions = regionService.getAllRegions();
        return ResponseEntity.ok(regions);
    }

    @GetMapping("/get-region")
    public ResponseEntity<List<Region>> getRegion(@RequestParam Integer countryId) {
        List<Region> regions = regionService.getRegionByCountry(countryId);
        return ResponseEntity.ok(regions);
    }
}
