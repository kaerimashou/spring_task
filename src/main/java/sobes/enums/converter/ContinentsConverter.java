package sobes.enums.converter;

import sobes.enums.Continents;

import javax.persistence.AttributeConverter;

public class ContinentsConverter implements AttributeConverter<Continents,String> {
    @Override
    public String convertToDatabaseColumn(Continents continents) {
        return continents.getName();
    }

    @Override
    public Continents convertToEntityAttribute(String s) {
        switch(s){
            case "Asia":
                return Continents.Asia;
            case "Europe":
                return Continents.Europe;
            case "North America":
                return Continents.North_America;
            case "Africa":
                return Continents.Africa;
            case "Oceania":
                return Continents.Oceania;
            case "Antarctica":
                return Continents.Antarctica;
            case "South America":
                return Continents.South_America;
        }
        return Continents.Asia;
    }
}
