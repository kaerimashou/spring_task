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
    public static final String sql3 = "SELECT c.name," +
            "                          cl.countrycode," +
            "                          c.continent\n" +
            "FROM countrylanguage cl\n" +
            "INNER JOIN country c on code=countrycode\n" +
            "INNER JOIN (SELECT name\n" +
            "\t\t\tFROM countrylanguage\n" +
            "            INNER JOIN country on code=countrycode\n" +
            "            WHERE language=?) as fr on fr.name=c.name\n" +
            "INNER JOIN (SELECT name\n" +
            "\t\t\tFROM countrylanguage\n" +
            "            INNER JOIN country on code=countrycode\n" +
            "            GROUP BY name\n" +
            "            HAVING count(*)=2) as cnt on cnt.name=c.name\n" +
            "WHERE language=?";
    public static final String sql4 = "SELECT c.name,c.Population,c.continent,c.surfacearea\n" +
            "FROM country c\n" +
            "WHERE population>?";
    public static final String sql5 = "SELECT c.name,\n" +
            "\t   c.Capital,\n" +
            "       ct.population as CapitalPopulation,\n" +
            "       c.population as CountryPopulation,\n" +
            "       c.SurfaceArea\n" +
            "FROM country c\n" +
            "INNER JOIN city ct ON Capital=id\n" +
            "WHERE ct.Population>c.Population-ct.Population";
    public static final String sql6 = "SELECT DISTINCT c.name,\n" +
            "\t\t\t\tc.Continent,\n" +
            "                c.Region,\n" +
            "                c.Population,\n" +
            "                c.SurfaceArea,\n" +
            "                c.Capital\n" +
            "FROM world.countrylanguage cl\n" +
            "INNER JOIN country c ON code=CountryCode\n" +
            "WHERE name NOT IN(SELECT name\n" +
            "\t\t\t\t  FROM countrylanguage\n" +
            "                  INNER JOIN country ON code=CountryCode\n" +
            "                  WHERE language=?)";
    public static final String sql7 = "SELECT c.name,\n" +
            "       c.Continent,\n" +
            "       c.region,\n" +
            "       c.HeadOfState,\n" +
            "       c.GovernmentForm,\n" +
            "       ct.Name as Capital,\n" +
            "       c.population\n" +
            "FROM countrylanguage cl\n" +
            "INNER JOIN country c ON code=countrycode\n" +
            "INNER JOIN city ct ON capital=id\n" +
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
