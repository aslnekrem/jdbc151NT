import org.junit.Test;

import static org.junit.Assert.*;

public class JunitTest01 {

    //Artik main method kullanmayacagiz @Test ile Run butonu geliyor
    @Test
    public void test01(){ //Test methodlari public ve void olmak zorundadir

        assertEquals(5,"hello".length()); //assertEquals() methodununda parantez icindeki parametreler birbirine esitse gecer, degilse kalir
        assertTrue("Merhaba".contains("a")); //assertTrue() methodunun parantez icindeki kosul true ise test gecer, degilse kalir
        assertFalse("google.com".contains("x")); //negative test --> Parantez ici durum False dönerse test gecer
    }

//    asertTrue()
//    asertEquals()



}
