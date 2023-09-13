import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CountryTest {

 /*
 Given (ön kosullar)
   User connects to the database
   (Kullanıcı veritabanına bağlanır)

 When (yapilacak islemler)
   User sends the query to get the region ids from "countries" table
   (Kullanıcı, 'countries' table'dan 'region id' almak üzere query gönderir )

 Then (Cikti kismi, Dogrulama)
   Assert that the number of region ids greater than 1 is 17.
   (1'den büyük region id'lerin sayısının 17 olduğunu doğrula )

 And (Then isleminden iki tane var demek yani bir den fazla then yazmiyoruz kalnlar icin And yaziyoruz)
   User closes the connection
*/

    @Test
    public void countryTest() throws SQLException { //Test method'unun parametresi olmaz
        //User connects to the database
        JdbcUtils.connectToDatabase();
        JdbcUtils.createStatement();

        //User sends the query to get the region ids from "countries" table
        String sql = "SELECT COUNT(region_id) FROM countries WHERE region_id >1";
        ResultSet resultSet = JdbcUtils.executeQuery(sql);

        //Assert that the number of region ids greater than 1 is 17.
        resultSet.next();
        int result = resultSet.getInt(1);
        assertEquals(17,result);

        //User closes to connection

        JdbcUtils.closeConnection();


    }



    @Test
    public void countryTest2() throws SQLException {
        JdbcUtils.connectToDatabase();
        JdbcUtils.createStatement();

        String sql = "SELECT region_id FROM countries WHERE region_id >1";
        ResultSet resultSet = JdbcUtils.executeQuery(sql);
        //resultSet.getMetaData(); bu sekilde Column ismini alabiliriz istersek

        List<Integer> region_idList = region_idList = new ArrayList<>();
        while (resultSet.next()){
            region_idList.add(resultSet.getInt(1));
        }
        System.out.println("region_idList = " + region_idList);

        //Assert that the number of region ids greater than 1 is 17
        assertEquals(17,region_idList.size());

        //User closes the connection
        JdbcUtils.closeConnection();

    }









}//class
