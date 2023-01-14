package serve.tic.tac.toe;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLNonTransientConnectionException;
import javafx.application.Platform;

public class MessageHandler extends Thread {

    private DataInputStream recive;
    private DataOutputStream send;
    public Socket socket;
    private String message;
    public volatile boolean isRunning=false;
    public int clientID = -1;
    public String clientName="";
    public boolean isInAvilable=false;
    public boolean isPlayingInAGame=false;
    public MessageHandler(Socket s) {
        try {
            message = "";
            socket = s;isRunning=true;
            recive = new DataInputStream(socket.getInputStream());
            send = new DataOutputStream(socket.getOutputStream());
            start();
        } catch (Exception ex) {

            System.out.print(ex.getMessage());

        }
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                if (recive != null) {
                    message = recive.readUTF();
                    String check[] = message.split(",");
                    if ("Move".equals(check[0])) {
                        for (MessageHandler player : Server.myClients) {

                            if (player.clientID == Integer.valueOf(check[1])) {
                                message = check[0] + "," + check[2];
                                System.err.println(message);
                                player.send.writeUTF(message);
                                break;
                            }

                        }
                    }
                    else if (message.equals("Avaliable")) {
                        ResultSet resultSet;isInAvilable=true;
                        resultSet = Server.operations.database.executeSelect("Select * from ROOT.PLAYERS where STATUS = true");

                        String messageAvaliable = "Avaliable,";
                        while (resultSet.next()) {
                            if(resultSet.getInt(1)!=clientID&&!getIsInGameCheck(resultSet.getInt(1)))
                            messageAvaliable += resultSet.getInt(1) + "," + resultSet.getString(2) + ",";

                        }
                        
                        messageAvaliable = messageAvaliable.substring(0, messageAvaliable.length() - 1);
                        System.out.println(messageAvaliable);
                        send.writeUTF(messageAvaliable);
                        

                    }else if(message.equals("closeAv"))
                    {
                        this.isInAvilable=false;
                    }
                    else if (check[0].equals("login")) {
                        String dbResult = Server.operations.logInCheck(message);

                        if (dbResult.length() > 6) {//if less than 6 then or equal the there is no data in database
                            clientID = Integer.valueOf(dbResult.split(",")[1]);
                            clientName=dbResult.split(",")[2];
                            Platform.runLater(()->{ PlayersScreenBase.fromOffToOn(clientName);});
                            Server.operations.database.changePlayerStatus(clientID, true);
                            if(Server.myClients.size()>1){
                                for(MessageHandler handler:Server.myClients)
                                {
                                    if(handler!=this)
                                    {
                                        handler.send.writeUTF("UpdateAddAv,"+clientID+","+clientName);
                                    }
                                }
                            
                            }
                        }
                        send.writeUTF(dbResult);
                        
                       
                    }
                    else if (check[0].equals("signUp")) {
                        boolean result = Server.operations.SignUp(message);
                        String repleyMessage = "signUp," + String.valueOf(result);
                        System.out.println(repleyMessage + "////////////////////////");
                        send.writeUTF(repleyMessage);

                    }
                    else if (check[0].equals("wins")) {
                        Server.operations.database.setPGamesWins(1, clientID, Integer.valueOf(check[1]));
                    }
                    else if (check[0].equals("PGames")) {
                            try{
                                Server.operations.database.setPGamesWins(2, clientID, Integer.valueOf(check[1]));
                            }catch(SQLNonTransientConnectionException ex){
                                System.out.println(ex.getMessage());
                            }
                    }
                    else if (check[0].equals("endGame")) {
                        for (MessageHandler client : Server.myClients) {

                            if (client.clientID == Integer.valueOf(check[1])) {
                                
                                client.send.writeUTF("endGame,");
                                break;
                            }

                        }
                    }
                   else if(check[0].equals("invite")){
                       for(MessageHandler handler:Server.myClients){
                           if(handler.clientID==Integer.valueOf(check[1])){
                               
                               handler.send.writeUTF(check[0]+","+clientID+","+check[2]);
                               
                               break;
                           }
                       }
                   }
                   else if(check[0].equals("acceptInvitation")){
                       for(MessageHandler handler:Server.myClients){
                           if(handler.clientID==Integer.valueOf(check[1])){
                               for(MessageHandler h:Server.myClients)
                               {
                                  if(h!=handler&&h!=this){
                                     h.send.writeUTF("UpdateRemAv,"+handler.clientID+","+handler.clientName);
                                     h.send.writeUTF("UpdateRemAv,"+clientID+","+clientName);
                                  }
                               }
                               handler.isPlayingInAGame=true;
                               handler.send.writeUTF("startGame,"+clientID);
                               break;
                           }
                       }
                        isPlayingInAGame=true;
                        send.writeUTF("startGame,"+check[1]);
                   }
                   else if(check[0].equals("rejectInvitation")){
                       for(MessageHandler handler:Server.myClients){
                           if(handler.clientID==Integer.valueOf(check[1])){
                               handler.send.writeUTF("rejectInvitation,"+clientID);
                               break;
                           }
                       }
                   }else if(check[0].equals("playing"))
                   {
                      for(MessageHandler handler:Server.myClients)
                      {
                          if(handler.clientID==Integer.valueOf(check[1]))
                          {
                              handler.send.writeUTF("playing,"+clientID);
                              break;
                          }
                      }
                   }else if(check[0].equals("Close"))
                   {
                        System.err.println("Clint Sends Close");
                        isRunning=false;
                        Platform.runLater(()->{PlayersScreenBase.fromOnToOff(clientName);});
                        Server.operations.database.changePlayerStatus(clientID, false);
                        for(MessageHandler handler:Server.myClients)
                            {
                                if(handler!=this)
                                {
                                    handler.send.writeUTF("UpdateRemAv,"+clientID+","+clientName);
                                }
                            }
                        recive.close();
                        send.close();
                        socket.close();
                        this.stop();
                        Server.myClients.remove(this);
                   }else if(check[0].equals("Clear"))
                   {
                       Platform.runLater(()->{PlayersScreenBase.fromOnToOff(clientName);});
                       Server.operations.database.changePlayerStatus(clientID, false);
                       System.err.println("here");
                       System.err.println("There");
                      for(MessageHandler handler:Server.myClients)
                            {
                                if(handler!=this)
                                {
                                    handler.send.writeUTF("UpdateRemAv,"+clientID+","+clientName);
                                }
                            }
                       
                       clientID=-1;
                       clientName="";
                       
                   }else if(check[0].equals("EndGameSession"))
                   {
                       isPlayingInAGame=false;
                       for(MessageHandler handler:Server.myClients)
                        {
                            if(handler!=this)
                            {
                                handler.send.writeUTF("UpdateAddAv,"+clientID+","+clientName);
                            }
                        }
                   }
                    
                }
                if(Server.isRunning==false){
                    
                    isRunning=false;
                }
            }
           
        }
        catch (Exception e) {
            
            System.out.println(e.getCause());
        }
        
        
    }
    public void sendMessage(String message)
    {
        try {
            send.writeUTF(message);
        } catch (IOException ex) {
         System.out.println(ex.getMessage());
        }
    }
    private boolean getIsInGameCheck(int id)
    {
        boolean res=false;
        for(MessageHandler handler:Server.myClients)
        {
            if(handler.clientID==id)
            {
                res=handler.isPlayingInAGame;
                break;
            }
        }
        return res;
    }
}