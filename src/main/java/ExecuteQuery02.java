import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgresql");
        Statement statement =  connection.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.


        //1. Way
        String sql1 = "SELECT company,number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 LIMIT 1";
        ResultSet resultSet1 = statement.executeQuery(sql1);
        while (resultSet1.next()){//ResultSet son satira gelip "false" dondukten sonra kapatilir. Kapali ResultSet uzerinde islem yapilamaz, yapilirsa exception atar.
            System.out.println(resultSet1.getString("company")+"--"+resultSet1.getString("number_of_employees"));

        }

        //2. Way
        String sql2 = "SELECT company, number_of_employees  FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees) FROM companies\n" +
                " WHERE number_of_employees < (SELECT MAX(number_of_employees) FROM companies))";

        ResultSet rs2 = statement.executeQuery(sql2);
        while (rs2.next()) {
            System.out.println(rs2.getString(1)+ "--" +rs2.getObject(2));
        }








        //resultSet1.close(); böyle resultset'i de kapatabiliriz ama cok önemli degil connection kapaniyor zaten
        connection.close();
        statement.close();
    }//main
}//class
