
package serve.tic.tac.toe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.derby.jdbc.ClientDriver;


public class Database {
    private Connection con;
    private PreparedStatement statement;
    
    public Database(){
      
        try {
            DriverManager.registerDriver(new ClientDriver());
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Tic-Tac-Toe", "root","root");
           
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
    public ResultSet executeSelect(String query)
    {
        ResultSet setData=null;
        try {
             setData=con.prepareStatement(query).executeQuery();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        return setData;
    }
    
    public void close(){
    
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }
    
}
