
package serve.tic.tac.toe;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServeTicTacToe extends Application {
    public static Scene scene;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(e->{
          try{
            MainScreenBase.ourServer.close();
          }catch(Exception ex){
              System.out.println(ex.getCause());
          }
        });
        primaryStage.setResizable(false);
        
         scene = new Scene(new SplashScreenBase(), 730, 470);
        
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
       
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServeTicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                }
           
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ServeTicTacToe.scene.setRoot(new MainScreenBase());              
                    }
                });
                 
            }
        }).start();
      
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
