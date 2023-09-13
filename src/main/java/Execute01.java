import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    // POM: Page Object Modul
    // JDBC: icin postgresql kullanacagiz
    //Pom.xml'e ekledigimiz library'ler ile calisiyoruz testler'de.
    //Dependency'i Maven Repository'den alabiliriz ama virüs bulasma gibi ihtimaller var bu yüzden normalde sirketten
        // alinir. Mülakatlarda sorulabilir bu soru
    //Maven reporsitory'e Ornek olarak: https://mvnrepository.com/artifact/org.postgresql/postgresql/42.6.0

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.Adim: Driver'a kaydol
        Class.forName("org.postgresql.Driver");//JDBC 4 sonrasi bunu yapmaya gerek yok

        //2. Adim: Connection' olusturmak. Database'e baglan

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgresql");
        // jdbc:postgresql://  --> Bu kisim sabit
        //DRY = Do not Repeat Yourself(Tekrarsiz Code)
        //WET = Write Everything Twice(Islak Code, her seyi iki kere yap)
        //Yazdigimiz Code, DRY olmali

        //3. Adim: Statement olustur.
        Statement statement =  connection.createStatement();

        //4. Adim: Query calistir

        /*
        execute() methodu DDL(create, drop, alter table) ve DQL(select) ile kullanılır
        1) Eğer execute() methodu DDL ile kullanılırsa hep "false" döner. Çünkü DDL ile data çağrılmaz
        2) Eğer execute() methodu DQL ile kullanılırsa data döndüğünde "true" data dönmediğinde "false" verir.
        */

        //1. Örnek: CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)
        String sql1 = "CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);//false
        //execute method: SQL komutlarini girdigimiz method
        //execute method: Data cagirirsak true cagirmazsak false döndürür

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.

        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR(100)";
        boolean r2 = statement.execute(sql2);
        System.out.println("r2 = " + r2);//false

        //3.Örnek: workers table'ını silin.

        String sql3 ="DROP TABLE workers";
        boolean r3 = statement.execute(sql3);
        System.out.println("r3 = " + r3);//false

        //5. Adim: Baglantiyi kapat
        connection.close();
        statement.close();

    }//main
}
