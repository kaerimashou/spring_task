package sobes.entities;

import sobes.enums.IsOfficial;

import javax.persistence.*;

@Entity
@Table(name="countrylanguage")
public class CountryLanguage {
    @Id
    @PrimaryKeyJoinColumn(name="CountryCode",referencedColumnName = "CountryCode")
    private String countryCode;

    @Column(name="Language")
    private String language;

    @Column(name="IsOfficial")
    @Enumerated(EnumType.STRING)
    private IsOfficial isOfficial;

    @Column(name="Percentage")
    private Double percentage;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public IsOfficial getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(IsOfficial isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "CountryLanguage{" +
                "countryCode='" + countryCode + '\'' +
                ", language='" + language + '\'' +
                ", isOfficial=" + isOfficial.toString() +
                ", percentage=" + percentage +
                '}';
    }
}
