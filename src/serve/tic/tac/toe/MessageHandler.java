package serve.tic.tac.toe;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.ResultSet;

public class MessageHandler extends Thread {

    private DataInputStream recive;
    private DataOutputStream send;
    ResultSet resultSet;
    String messageAvaliable = "Avaliable,";
    private Socket socket;
    private String message;

    public int clientID = -1;

    public MessageHandler(Socket s) {
        try {
            message = "";
            socket = s;
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
            while (true) {
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

                        resultSet = Server.operations.database.executeSelect("Select * from ROOT.PLAYERS where STATUS = true");

                        while (resultSet.next()) {

                            messageAvaliable += resultSet.getInt(1) + "," + resultSet.getString(2) + ",";

                        }

                        messageAvaliable = messageAvaliable.substring(0, messageAvaliable.length() - 1);
                        System.out.println(messageAvaliable);
                        send.writeUTF(messageAvaliable);

                    }
                    else if (check[0].equals("login")) {
                        String dbResult = Server.operations.logInCheck(message);

                        if (dbResult.length() > 6) {
                            clientID = Integer.valueOf(dbResult.split(",")[1]);
                            Server.operations.database.changePlayerStatus(clientID, true);

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
                        Server.operations.database.setPGamesWins(2, clientID, Integer.valueOf(check[1]));
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
                               handler.send.writeUTF("startGame,"+clientID);
                               break;
                           }
                       }
                       send.writeUTF("startGame,"+check[1]);
                   }
                   else if(check[0].equals("rejectInvitation")){
                       for(MessageHandler handler:Server.myClients){
                           if(handler.clientID==Integer.valueOf(check[1])){
                               handler.send.writeUTF("rejectInvitation,"+clientID);
                               break;
                           }
                       }
                   }
                }
            }
        }
        catch (Exception e) {
            Server.operations.database.changePlayerStatus(clientID, false);
            this.stop();
            Server.myClients.remove(this);
            System.out.println(e.getCause());
        }
    }
}
