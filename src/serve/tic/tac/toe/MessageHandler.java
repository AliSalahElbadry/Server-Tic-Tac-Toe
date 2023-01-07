
package serve.tic.tac.toe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class MessageHandler extends Thread{

    private DataInputStream recive;
    private DataOutputStream send;
    
    private Socket socket;
    private String message;
   
    public int clientID=-1;
    
    public MessageHandler(Socket s){
        try{
            message="";
            socket=s;
            recive=new DataInputStream(socket.getInputStream());
            send=new DataOutputStream(socket.getOutputStream());
            
            start();
        }catch(Exception e)
        {
            System.out.println(e.getCause());
        }
    }
    @Override
    public void run() {
        super.run(); 
        while(true){
            try {
                
                if(recive!=null){
                   message=recive.readUTF();
                    
                   String check[]=message.split(",");
                   if("Move".equals(check[0]))
                   {
                       String[]words=message.split(",");
                       for (MessageHandler player :Server.myClients) {
                           
                           if(player.clientID==Integer.valueOf(words[1]))
                           {
                               message=words[0]+","+words[2];
                               player.send.writeUTF(message);
                               System.err.println(message);
                           }
                           
                       }
                   }

                  
                   if(check[0].equals("login"))
                   {
                     String dbResult=Server.operations.logInCheck(message);
                      
                     if(dbResult.length()>6)
                     {
                         clientID=Integer.valueOf(dbResult.split(",")[1]);
                         Server.operations.database.changePlayerStatus(clientID, true);
                     
                     }
                     send.writeUTF(dbResult); 
                   }
                   else if(check[0]=="signUp")
                   {
                       Server.operations.SignUp(message);
                   }
                    

                }
            } catch (Exception ex) {
                Server.operations.database.changePlayerStatus(clientID, false);
                  this.stop();
                  Server.myClients.remove(this);
                  System.out.print(ex.getMessage());
            }
        }
       
    }
}
