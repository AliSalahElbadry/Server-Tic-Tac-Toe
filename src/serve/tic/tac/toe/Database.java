
package serve.tic.tac.toe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public void changePlayerStatus(int playerID,boolean status)
   {
        try {
           PreparedStatement  statement=con.prepareStatement("update ROOT.PLAYERS set STATUS=? where PLAYER_ID=?");
           statement.setBoolean(1, status);
           statement.setInt(2, playerID);
           statement.executeUpdate();
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
       
       
   }
   
    
    public void close(){
    
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }
    
}
