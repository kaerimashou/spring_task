package sobes.entities;

import org.springframework.lang.Nullable;
import sobes.enums.Continents;
import sobes.enums.converter.ContinentsConverter;

import javax.persistence.*;

@Entity
@Table(name="country")
public class Country {

    @Id
    @Column(name="CountryCode")
    private String countryCode;

    @Column(name="Name")
    private String name;

    @Column(name="Continent")
    @Convert(converter = ContinentsConverter.class)
    private Continents continent;

    @Column(name="Region")
    private String region;

    @Column(name="SurfaceArea")
    private Double surfaceArea;

    @Column(name="IndepYear")
    @Nullable
    private Integer independencyYear;

    @Column(name="Population")
    private Long population;

    @Column(name="LifeExpectancy")
    @Nullable
    private Double lifeExpectancy;

    @Column(name="GNP")
    private Double GNP;

    @Column(name="GNPOld")
    @Nullable
    private Double GNPOld;

    @Column(name="LocalName")
    private String localName;

    @Column(name="GovernmentForm")
    private String governmentForm;

    @Column(name="HeadOfState")
    @Nullable
    private String headOfState;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="Capital",referencedColumnName = "ID")
    private City capital;

    @Column(name="Code2")
    private String secondCode;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Continents getContinent() {
        return continent;
    }

    public void setContinent(Continents continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Integer getIndependencyYear() {
        return independencyYear;
    }

    public void setIndependencyYear(Integer independencyYear) {
        this.independencyYear = independencyYear;
    }

    @Override
    public String toString() {
        return "Country{" +
                "capital=" + capital +
                '}';
    }
}
