package serve.tic.tac.toe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server extends Thread{
    ServerSocket serverSocket;
    public static volatile DatabaseOperations operations;
    public static volatile Vector<MessageHandler>myClients=new Vector<MessageHandler>();
    public static boolean isRunning=false;
    public Server(){
          operations=new DatabaseOperations();
        try {
            serverSocket=new ServerSocket(5005);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void run() {
     
        while(true)
        {
            if(Server.isRunning==false)break;
            try {
               Socket socket=serverSocket.accept();
               Server.myClients.add( new MessageHandler(socket));//here set status true
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void close()
    {
        try 
        {
            operations.database.close();
            myClients.forEach(Thread::stop);
            
            this.serverSocket.close();
            this.stop();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}