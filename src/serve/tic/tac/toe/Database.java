package serve.tic.tac.toe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
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
             if(con.isClosed()||con==null)
                {
                    DriverManager.registerDriver(new ClientDriver());
                    con=DriverManager.getConnection("jdbc:derby://localhost:1527/Tic-Tac-Toe", "root","root");
                }
             setData=con.prepareStatement(query).executeQuery();
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        return setData;
    }
    
    public void changePlayerStatus(int playerID,boolean status)
   {
        try {
            if(con.isClosed()||con==null)
            {
                DriverManager.registerDriver(new ClientDriver());
                con=DriverManager.getConnection("jdbc:derby://localhost:1527/Tic-Tac-Toe", "root","root");
            }
           statement=con.prepareStatement("update ROOT.PLAYERS set STATUS=? where PLAYER_ID=?");
           statement.setBoolean(1, status);
           statement.setInt(2, playerID);
           statement.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
       
   }
    public int insertPlayer(int playerId,String username,String email,String password){
        int res=-1;
        try {
            if(con.isClosed()||con==null)
            {
                DriverManager.registerDriver(new ClientDriver());
                con=DriverManager.getConnection("jdbc:derby://localhost:1527/Tic-Tac-Toe", "root","root");
            }
            statement=con.prepareStatement("insert into ROOT.PLAYERS (PLAYER_ID,USER_NAME,EMAIL,PASSWORD,STATUS) values (?,?,?,?,?)");
            statement.setInt(1,playerId);
            statement.setString(2, username);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setBoolean(5, false);
            res=statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    public void setPGamesWins(int mode,int Id,int value)throws SQLNonTransientConnectionException
//0 means pgames , 1 means wins
    {
       String query=""; 
       if(mode==0)
       {
           query="Update Players set COUNTGAMES=? where PLAYER_ID=?";
       }else if(mode==1)
       {
           query="Update Players set WINS=? where PLAYER_ID=?";
       }
        try {
            if(con.isClosed()||con==null)
            {
                DriverManager.registerDriver(new ClientDriver());
                con=DriverManager.getConnection("jdbc:derby://localhost:1527/Tic-Tac-Toe", "root","root");
            }
            statement=con.prepareStatement(query);
            statement.setInt(1, value);
            statement.setInt(2, Id);
            statement.executeUpdate();
        } catch (SQLNonTransientConnectionException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
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