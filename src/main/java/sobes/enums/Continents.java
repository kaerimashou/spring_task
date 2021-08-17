package sobes.enums;

public enum Continents {
    Asia("Asia"),
    Europe("Europe"),
    North_America("North America"),
    Africa("Africa"),
    Oceania("Oceania"),
    Antarctica("Antarctica"),
    South_America("South America");

    private final String name;

    Continents(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }
}
