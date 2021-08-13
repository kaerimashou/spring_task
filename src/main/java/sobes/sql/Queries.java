package sobes.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {

    public static final String sql1 = "SELECT name\n" +
            "FROM countrylanguage\n" +
            "INNER JOIN country ON code=CountryCode\n" +
            "WHERE language='English'";
    public static final String sql2 = "SELECT name\n" +
            "FROM countrylanguage\n" +
            "INNER JOIN country ON code=CountryCode\n" +
            "GROUP BY CountryCode\n" +
            "HAVING count(*)>3";
    public static final String sql3 = "SELECT country.name\n" +
            "FROM countrylanguage\n" +
            "INNER JOIN country on code=countrycode\n" +
            "INNER JOIN (SELECT name\n" +
            "\t\t\tFROM countrylanguage\n" +
            "            INNER JOIN country on code=countrycode\n" +
            "            WHERE language=\"French\") as fr on fr.name=country.name\n" +
            "INNER JOIN (SELECT name\n" +
            "\t\t\tFROM countrylanguage\n" +
            "            INNER JOIN country on code=countrycode\n" +
            "            GROUP BY name\n" +
            "            HAVING count(*)=2) as cnt on cnt.name=country.name\n" +
            "WHERE language=\"English\"";
    public static final String sql4 = "SELECT name\n" +
            "FROM country\n" +
            "WHERE population>10000000";
    public static final String sql5 = "SELECT country.name\n" +
            "FROM country\n" +
            "INNER JOIN city ON Capital=id\n" +
            "WHERE city.Population>country.Population-city.Population";
    public static final String sql6 = "SELECT DISTINCT country.name\n" +
            "FROM world.countrylanguage\n" +
            "INNER JOIN country ON code=CountryCode\n" +
            "WHERE name NOT IN(SELECT name\n" +
            "\t\t\t\t  FROM countrylanguage\n" +
            "                  INNER JOIN country ON code=CountryCode\n" +
            "                  WHERE language=\"English\") \n";
    public static final String sql7 = "SELECT name\n" +
            "FROM countrylanguage\n" +
            "INNER JOIN country ON code=countrycode\n" +
            "GROUP BY name\n" +
            "HAVING count(*)=(SELECT MAX(languageCount)\n" +
            "\t\t\t\t\t  FROM (SELECT name,count(*) as languageCount\n" +
            "\t\t\t\t\t\t\tFROM countrylanguage\n" +
            "                            INNER JOIN country ON code=countrycode\n" +
            "                            GROUP BY name) AS cnt);";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "roots", "root");
            ResultSet firstSet = connection.prepareStatement(sql1).executeQuery();
            ResultSet secondSet = connection.prepareStatement(sql2).executeQuery();
            ResultSet thirdSet = connection.prepareStatement(sql3).executeQuery();
            ResultSet fourthSet = connection.prepareStatement(sql4).executeQuery();
            ResultSet fifthSet = connection.prepareStatement(sql5).executeQuery();
            ResultSet sixthSet = connection.prepareStatement(sql6).executeQuery();
            ResultSet seventhSet = connection.prepareStatement(sql7).executeQuery();
            System.out.println("English-speaking countries:");
            execute(firstSet);
            System.out.println("Countries with more than 3 languages: ");
            execute(secondSet);
            System.out.println("Countries with English and French");
            execute(thirdSet);
            System.out.println("Countries with more than 10000000 population");
            execute(fourthSet);
            System.out.println("Countries where capital population is more than the rest of the country");
            execute(fifthSet);
            System.out.println("Countries without English as official language");
            execute(sixthSet);
            System.out.println("Countries with greatest amount of official languages");
            execute(seventhSet);
        } catch (Exception e) {
            System.out.println("Connection failed");
            System.out.println(e);
        }
    }

    public static void execute(ResultSet resultSet) throws SQLException {
        int i = 1;
        while (resultSet.next()) {
            System.out.println(i + ": " + resultSet.getString("name"));
            i++;
        }

    }
}
