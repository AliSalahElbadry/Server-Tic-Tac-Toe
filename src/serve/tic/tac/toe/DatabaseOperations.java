
package serve.tic.tac.toe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseOperations {
      Database database;
   public DatabaseOperations(){
       
       database=new Database();
   }
   
   public String logInCheck(String comingData)
   {
      String[]allData=comingData.split(",");
      String res="";
      ResultSet data=database.executeSelect("select * from ROOT.PLAYERS where Email="+allData[1]+" and password="+allData[2]);
       try {
           while(data.next())
           {
               res+=data.getInt("PLAYER_ID")+","+data.getInt("USER_Name")+","+
                    data.getInt("EMAIL")+","+data.getInt("PASSWORD")+","+data.getInt("STATUS")+","+
                    data.getInt("WINS")+","+data.getInt("COUNTGAMES");
           }
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
       }
       return res;
   }
   public boolean SignUp(String comingData)//data comes as one string with , splitter
   {
       boolean result=true;
       String[]allData=comingData.split(",");
       //check user if exists
       ResultSet check_exists=database.executeSelect("select * from PLAYERS where EMAIL="+allData[1]);
       try {
           if(check_exists.next())
           {
               result=false;
           }
      
       if(result){
           //get raws count
           check_exists=database.executeSelect("select count(*) from PLAYERS");
           
           
      // result=database.ExecuteUpdate("INSERT INTO PLAYERS VALUES ("+(Integer.valueOf(check_exists.getInt(0))+1)+allData[0]+","+allData[1]+","+allData[2]+","+"true,0,0)");
       }
       } catch (SQLException ex) {
           Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
       }
      return result;
   }
   public void setStatus(boolean status,int id){
       String stat=status?"true":"false";
     //  database.ExecuteUpdate("update PLAYERS set STATUS ="+stat+" where PLAYER_ID="+id);
   }
   
}
