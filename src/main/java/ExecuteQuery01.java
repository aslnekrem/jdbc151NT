import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgresql");

        Statement statement =  connection.createStatement();



        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);//true ==> DQL ile data cagiriyoruz

        //Datayi cagirip okumak icin executeQuery methodunu kullanmaliyiz. execute() method'u sadece true ya da false döner
        ResultSet resultSet = statement.executeQuery(sql1);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }

        resultSet.next(); //Java'daki Iterator gibi. Bunu yaparak 1. satira gittik mesela
        resultSet.next();//2. satir
        resultSet.next();//3. satir
        resultSet.next();//4. satir

        //System.out.println(resultSet.getString("country_name")); //country_name sütununu oku dedik
        //eger cok column olsaydi mesela 1 yazsaydik birinci column'i bize döndür derdik

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

        String sql2 = "SELECT country_id,country_name FROM countries WHERE region_id>2";
        ResultSet resultSet2 = statement.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString(1)+"--"+resultSet2.getString(2));
            //getString(), kullanmamizin sebebi String olarak görmek ve istersek String method'larini kullanip islem yapabilmek icin
            //Fakat istersek Object(), getInt(), vs diyerek de alabiliriz...
        }
        //NOT: Hic next(), yapmazsak column name'ler geliyor cünkü pointer hic ilerlememis ve column'larda kalmis oluyor

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.



        String sql3 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet resultSet3 = statement.executeQuery(sql3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getString(1)+"--"+resultSet3.getString(2)+"--"+resultSet3.getString(3));
            //1-2-3 yazdigimiz sütun yerlerine sütun adlarini da yazabiliriz
        }

        while (resultSet3.next()){
            System.out.println(resultSet3.getString(1)+"--"+resultSet3.getString(2)+"--"+resultSet3.getString(3));

        }

        connection.close();
        statement.close();




    }//main
}//class
