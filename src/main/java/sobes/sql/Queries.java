package sobes.sql;


import java.sql.*;

public class Queries {

    public static final String sql1 = "SELECT name\n" +
            "FROM countrylanguage\n" +
            "INNER JOIN country ON code=CountryCode\n" +
            "WHERE language=?";
    public static final String sql2 = "SELECT countrycode,name\n" +
            "FROM countrylanguage\n" +
            "INNER JOIN country ON code=CountryCode\n" +
            "GROUP BY CountryCode\n" +
            "HAVING count(*)>?";
    public static final String sql3 = "SELECT country.name,countrycode,country.continent\n" +
            "FROM countrylanguage\n" +
            "INNER JOIN country on code=countrycode\n" +
            "INNER JOIN (SELECT name\n" +
            "\t\t\tFROM countrylanguage\n" +
            "            INNER JOIN country on code=countrycode\n" +
            "            WHERE language=?) as fr on fr.name=country.name\n" +
            "INNER JOIN (SELECT name\n" +
            "\t\t\tFROM countrylanguage\n" +
            "            INNER JOIN country on code=countrycode\n" +
            "            GROUP BY name\n" +
            "            HAVING count(*)=2) as cnt on cnt.name=country.name\n" +
            "WHERE language=?";
    public static final String sql4 = "SELECT name,Population,country.continent,country.surfacearea\n" +
            "FROM country\n" +
            "WHERE population>?";
    public static final String sql5 = "SELECT country.name,\n" +
            "\t   country.Capital,\n" +
            "       city.population as CapitalPopulation,\n" +
            "       country.population as CountryPopulation,\n" +
            "       country.SurfaceArea\n" +
            "FROM country\n" +
            "INNER JOIN city ON Capital=id\n" +
            "WHERE city.Population>country.Population-city.Population";
    public static final String sql6 = "SELECT DISTINCT country.name,\n" +
            "\t\t\t\tcountry.Continent,\n" +
            "                country.Region,\n" +
            "                country.Population,\n" +
            "                country.SurfaceArea,\n" +
            "                country.Capital\n" +
            "FROM world.countrylanguage\n" +
            "INNER JOIN country ON code=CountryCode\n" +
            "WHERE name NOT IN(SELECT name\n" +
            "\t\t\t\t  FROM countrylanguage\n" +
            "                  INNER JOIN country ON code=CountryCode\n" +
            "                  WHERE language=?)";
    public static final String sql7 = "SELECT country.name,\n" +
            "       country.Continent,\n" +
            "       country.region,\n" +
            "       country.HeadOfState,\n" +
            "       country.GovernmentForm,\n" +
            "       city.Name as Capital,\n" +
            "       country.population\n" +
            "FROM countrylanguage\n" +
            "INNER JOIN country ON code=countrycode\n" +
            "INNER JOIN city ON capital=id\n" +
            "GROUP BY name\n" +
            "HAVING count(*)=(SELECT MAX(languageCount)\n" +
            "\t\t\t\t FROM (SELECT name,count(*) as languageCount\n" +
            "\t\t\t\t\t   FROM countrylanguage\n" +
            "                       INNER JOIN country ON code=countrycode\n" +
            "                       GROUP BY name) AS cnt);";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "roots", "root");
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setString(1, "English");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("English-speaking countries:");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
            System.out.println("_________________________________________________");
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, 3);
            resultSet = preparedStatement.executeQuery();
            System.out.println("Countries with more than 3 languages: ");
            execute(resultSet);
            preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setString(1, "French");
            preparedStatement.setString(2, "English");
            resultSet = preparedStatement.executeQuery();
            execute(resultSet);
            preparedStatement = connection.prepareStatement(sql4);
            preparedStatement.setInt(1, 10000000);
            resultSet = preparedStatement.executeQuery();
            System.out.println("Countries with more than 10000000 population");
            execute(resultSet);
            preparedStatement = connection.prepareStatement(sql5);
            resultSet = preparedStatement.executeQuery();
            System.out.println("Countries where capital population is more than the rest of the country");
            execute(resultSet);
            preparedStatement = connection.prepareStatement(sql6);
            preparedStatement.setString(1, "English");
            resultSet = preparedStatement.executeQuery();
            System.out.println("Countries without English as official language");
            execute(resultSet);
            preparedStatement = connection.prepareStatement(sql7);
            resultSet = preparedStatement.executeQuery();
            System.out.println("Countries with greatest amount of official languages");
            execute(resultSet);
        } catch (Exception e) {
            System.out.println("Connection failed");
            System.out.println(e);
        }
    }

    public static void execute(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.print(resultSet.getString(metaData.getColumnLabel(i)) + " | ");
            }
            System.out.println();
        }
        System.out.println("_________________________________________________");
    }
}
