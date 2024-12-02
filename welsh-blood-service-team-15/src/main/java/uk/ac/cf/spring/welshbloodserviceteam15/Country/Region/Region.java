package uk.ac.cf.spring.welshbloodserviceteam15.Country.Region;


import jakarta.persistence.*;
import uk.ac.cf.spring.welshbloodserviceteam15.Country.Country;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regionId;

    private String regionName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
