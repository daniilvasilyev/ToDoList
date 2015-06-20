import contacts.Email;
import org.junit.Test;

import java.sql.*;

import static contacts.ContactType.HOME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование модели предметной области
 */
public class DBTest {
    @Test
    public void testDB() throws SQLException {
        Driver drv = new org.postgresql.Driver();

        Connection con;
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test",  "postgres", "12345" );

        Statement query = con.createStatement();
        ResultSet res = query.executeQuery( "SELECT * FROM task WHERE contact_id = 2" );

        while ( res.next() ) {
            System.out.println( res.getString( "name" ) );
        }
        res.close();
    }
}
