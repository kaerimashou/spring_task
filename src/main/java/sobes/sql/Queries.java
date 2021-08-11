package sobes.sql;


import java.sql.Connection;
import java.sql.DriverManager;

public class Queries {
    public static void main(String[] args){
        try{
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/testing?useUnicode=true&serverTimezone=UTC","root","qwerty96");

            System.out.println("Connection successful");
        }catch(Exception e){
            System.out.println("Connection failed");
        }
    }
}
