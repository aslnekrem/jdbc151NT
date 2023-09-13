import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgresql");
        Statement statement =  connection.createStatement();


        String sqlUpdate = "UPDATE companies SET number_of_employees = 16000 WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";
        int guncellenenSatirSayisi = statement.executeUpdate(sqlUpdate);
        //executeUpdate() methodu, güncellenen satir sayisi int deger olarak döner
        System.out.println(guncellenenSatirSayisi + " satır güncellendi.");

        //Güncelleme sonrası datayı okumak için DQL(Select) kullanıyoruz:
        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet = statement.executeQuery(sql2);

        while (resultSet.next()) {
            System.out.println(resultSet.getObject("company_id") + "--" +resultSet.getObject("company") + "--" + resultSet.getObject("number_of_employees"));
        }






        connection.close();
        statement.close();



    }//main
}//class
