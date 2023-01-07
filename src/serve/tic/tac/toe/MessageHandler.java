
package serve.tic.tac.toe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class MessageHandler extends Thread{
    public int clintID=-1;
    private DataInputStream recive;
    private DataOutputStream send;
    
    private Socket socket;
    private String message;
    
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
                    System.err.println(message);
                   if("Move".equals(message.split(",")[0]))
                   {
                       String[]words=message.split(",");
                       for (MessageHandler player :Server.myClients) {
                           
                           if(player.clintID==Integer.valueOf(words[1]))
                           {
                               message=words[0]+","+words[2];
                               player.send.writeUTF(message);
                               System.err.println(message);
                           }
                           
                       }
                   }
                }
            } catch (Exception ex) {
                  this.stop();
                  Server.myClients.remove(this);
                  System.out.print(ex.getMessage());
            }
        }
       
    }
}
