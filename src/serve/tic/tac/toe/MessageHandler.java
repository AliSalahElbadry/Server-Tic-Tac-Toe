package serve.tic.tac.toe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.ResultSet;

public class MessageHandler extends Thread {

    public DataInputStream recive;
    public DataOutputStream send;
    ResultSet resultSet;
    String messageAvaliable ="Avaliable,"; 

    Socket socket;
    String message;

    public MessageHandler(Socket s) {
        
        try {
            message = "";
            socket = s;
            recive = new DataInputStream(socket.getInputStream());
            send = new DataOutputStream(socket.getOutputStream());
            start();
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                if (recive != null) {
                    message = recive.readUTF();
                    if (message.equals("Avaliable")) {
                        System.out.println(message);
                        resultSet = Server.operations.database.executeSelect("Select * from ROOT.PLAYERS where STATUS = true");
                       
                        
                        while(resultSet.next()){
                            
                           messageAvaliable += resultSet.getInt(1)+","+resultSet.getString(2)+",";
                            
                        }
                       
                        messageAvaliable=messageAvaliable.substring(0,messageAvaliable.length()-1);
                        System.out.println(messageAvaliable);
                        send.writeUTF(messageAvaliable);

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
