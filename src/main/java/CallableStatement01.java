import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgresql");
        Statement statement = connection.createStatement();

        //1. Örnek: Selamlama yapan bir function'ı Callable Statement ile çağırınız
        //Callable Statement adimlari:
        //1. Adim: Function'i olusturan kodu yaziniz.


        String sql = "CREATE OR REPLACE FUNCTION selamlama(x TEXT) RETURNS TEXT AS $$ \n" +
                "BEGIN RETURN 'Merhaba '|| x ||', nasılsın?'; END; $$ LANGUAGE plpgsql;";

        //2. Adim: Function kodunu calistiriniz.

        statement.execute(sql);

        //3. Adim: Function'i cagir:

        CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}"); //bu sabit bir syntax

        //4. Adim: Return icin registerOutParameter() methodunu, parametreler icin ise setString, setInt gibi methodlari kullaniniz

        callableStatement.registerOutParameter(1,Types.VARCHAR); //Java'da Text kullanmadigimiz icin VARCHAR yaptik
        callableStatement.setString(2,"Ali");

        //5. Adim execute() methodu ile callableStatement' calistiriniz

        callableStatement.execute();

        //6. Adim: Sonucu okumak icin callableStatement'tan data türünü cagir
        //callableStatement'da dönen data resultset icinde dönmez. Direkt callableStatement icerinden alinir.
        System.out.println(callableStatement.getString(1));

        //2. Örnek: İki sayıyı toplayan bir function yazınız ve Callable Statement ile çağırınız.

        //1. Adim: Function'i olusturan kodu yaziniz.
        String sql2 = "CREATE OR REPLACE FUNCTION toplama(x numeric, y numeric) RETURNS integer AS $$ \n" +
                "BEGIN RETURN x+y; END; $$ LANGUAGE plpgsql;";

        //2. Adim: Function kodunu calistiriniz.
        statement.execute(sql2);

        //3. Adim: Function'i cagir:
        CallableStatement callableStatement2 = connection.prepareCall("{? = call toplama(?,?)}");

        //4. Adim: Return icin registerOutParameter() methodunu, parametreler icin ise setString, setInt gibi methodlari kullaniniz
        callableStatement2.registerOutParameter(1,Types.INTEGER);
        callableStatement2.setInt(2,5);
        callableStatement2.setInt(3,15);

        //5. Adim execute() methodu ile callableStatement' calistiriniz
        callableStatement2.execute();

        //6. Adim: Sonucu okumak icin callableStatement'tan data türünü cagir
        System.out.println(callableStatement2.getInt(1));



        connection.close();
        statement.close();


    }//main
}//class
