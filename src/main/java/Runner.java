import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {

        //Database'e baglan

        JdbcUtils.connectToDatabase();

        //Statement olustur

        JdbcUtils.createStatement();

        String sql = "CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)";
        JdbcUtils.execute(sql);









    }//main
}//class
