import java.sql.*;

public class JdbcUtils {

    //Bu Class'ta tekrarli yapilacak islemlerin methodlari bulunacak
    private static Connection connection;
    private static Statement statement;

    private static ResultSet resultSet;


    //2. Adim Database'e baglan

    public static Connection connectToDatabase(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgresql");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    //Medunna Database'e baglanma methodu

    //(Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))
    //yukaridakileri asagida nereye yerlestirdigimize dikkat et(Host name, Database name..., digerleri zaten sabit deger)
    public static Connection connectToMedunnaDatabase(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }







    //Statement olusturan method
    public static Statement createStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    //execute methodu ile query calistiran method olustur

    public static boolean execute(String sql){
        try {
           return statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //executeQuery(), executeUpdate(), closeConnection() methodlari ödev...

    //executeQuery() methodu ile query calistiran method
    public static ResultSet executeQuery(String sql){
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }

    //Baglantiyi kapatan method
    public static void closeConnection(){

        try {
            connection.close();
            statement.close();
            System.out.println("Baglanti kapatildi");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }











}//class
