package serve.tic.tac.toe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server extends Thread{
    public ServerSocket serverSocket;
    public static volatile DatabaseOperations operations;
    public static volatile Vector<MessageHandler>myClients;
    public static boolean isRunning=false;
    
    public Server(){
          operations=new DatabaseOperations();
          myClients=new Vector<>();
        try {
            serverSocket=new ServerSocket(5005);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void run() {
     
        while(isRunning)
        {
           
            try {
               Socket socket=serverSocket.accept();
               Server.myClients.add( new MessageHandler(socket));//here set status true
            } catch (SocketException ex) {
                System.out.println("ServerSocket Closed");
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void close()
    {
        try 
        {
            PlayersScreenBase.clearOnline();
            Server.myClients.forEach((handler) -> {
                try {
                    handler.sendMessage("Close,");
                    Server.operations.database.changePlayerStatus(handler.clientID, false);
                    handler.socket.close();
                } catch (SocketException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            operations.database.close();
            serverSocket.close();
            operations=null;
            myClients=null;
            this.stop();
        } catch (SocketException ex) {
            System.out.println("Server closed");
        }catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}