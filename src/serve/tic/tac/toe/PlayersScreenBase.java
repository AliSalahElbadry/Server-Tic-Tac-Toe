package serve.tic.tac.toe;

import java.net.URL;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayersScreenBase extends AnchorPane {

    protected final ImageView imageView;
    protected final Rectangle rectangle;
    protected final Rectangle rectangle0;
    protected final Text text;
    protected final Text text0;
    protected final Rectangle rectangle1;
    protected final Rectangle rectangle2;
    protected final Text text1;
    protected final Text text2;
    protected final ListView onlineListView;
    protected final ListView offlineListView;
    protected final ImageView backButtonId;

    public PlayersScreenBase() {

        imageView = new ImageView();
        rectangle = new Rectangle();
        rectangle0 = new Rectangle();
        text = new Text();
        text0 = new Text();
        rectangle1 = new Rectangle();
        rectangle2 = new Rectangle();
        text1 = new Text();
        text2 = new Text();
        onlineListView = new ListView();
        offlineListView = new ListView();
        backButtonId = new ImageView();

        setId("AnchorPane");
        setPrefHeight(480.0);
        setPrefWidth(750.0);
        getStylesheets().add("/serve/tic/tac/toe/playersscreen.css");

        imageView.setFitHeight(480.0);
        imageView.setFitWidth(750.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("Photos/background.jpg").toExternalForm()));

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.valueOf("#2a47ad"));
        rectangle.setHeight(300.0);
        rectangle.setLayoutX(103.0);
        rectangle.setLayoutY(84.0);
        rectangle.setStroke(javafx.scene.paint.Color.valueOf("#2a47ad"));
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.getStyleClass().add("boardCorner");
        rectangle.setWidth(270.0);

        rectangle0.setArcHeight(5.0);
        rectangle0.setArcWidth(5.0);
        rectangle0.setFill(javafx.scene.paint.Color.valueOf("#2a48aa"));
        rectangle0.setHeight(49.0);
        rectangle0.setLayoutX(132.0);
        rectangle0.setLayoutY(60.0);
        rectangle0.setStroke(javafx.scene.paint.Color.valueOf("#2a48aa"));
        rectangle0.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle0.getStyleClass().add("TextCorner");
        rectangle0.setWidth(209.0);

        text.setFill(javafx.scene.paint.Color.valueOf("#05ff05"));
        text.setLayoutX(156.0);
        text.setLayoutY(92.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Online ");
        text.setFont(new Font("Serif Regular", 25.0));

        text0.setFill(javafx.scene.paint.Color.WHITE);
        text0.setLayoutX(236.0);
        text0.setLayoutY(92.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("Players");
        text0.setFont(new Font("Serif Regular", 25.0));

        rectangle1.setArcHeight(5.0);
        rectangle1.setArcWidth(5.0);
        rectangle1.setFill(javafx.scene.paint.Color.valueOf("#2a47ad"));
        rectangle1.setHeight(300.0);
        rectangle1.setLayoutX(394.0);
        rectangle1.setLayoutY(84.0);
        rectangle1.setStroke(javafx.scene.paint.Color.valueOf("#2a47ad"));
        rectangle1.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle1.getStyleClass().add("boardCorner");
        rectangle1.setWidth(270.0);

        rectangle2.setArcHeight(5.0);
        rectangle2.setArcWidth(5.0);
        rectangle2.setFill(javafx.scene.paint.Color.valueOf("#2b49ab"));
        rectangle2.setHeight(49.0);
        rectangle2.setLayoutX(423.0);
        rectangle2.setLayoutY(60.0);
        rectangle2.setStroke(javafx.scene.paint.Color.valueOf("#2a48aa"));
        rectangle2.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle2.getStyleClass().add("TextCorner");
        rectangle2.setWidth(209.0);

        text1.setFill(javafx.scene.paint.Color.RED);
        text1.setLayoutX(447.0);
        text1.setLayoutY(92.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("Offline ");
        text1.setFont(new Font("Serif Regular", 25.0));

        text2.setFill(javafx.scene.paint.Color.WHITE);
        text2.setLayoutX(527.0);
        text2.setLayoutY(92.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("Players");
        text2.setFont(new Font("Serif Regular", 25.0));

        onlineListView.setLayoutX(117.0);
        onlineListView.setLayoutY(114.0);
        onlineListView.setPrefHeight(253.0);
        onlineListView.setPrefWidth(240.0);
        onlineListView.getStyleClass().add("mylistview");
        
        PlayerNameItemBase playerNameItemBase = new PlayerNameItemBase();
        playerNameItemBase.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase1 = new PlayerNameItemBase();
        playerNameItemBase1.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase2 = new PlayerNameItemBase();
        playerNameItemBase2.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase3 = new PlayerNameItemBase();
        playerNameItemBase3.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase4 = new PlayerNameItemBase();
        playerNameItemBase4.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase5 = new PlayerNameItemBase();
        playerNameItemBase5.playerNameId.setText("Fatma Hassan");
        
        onlineListView.getItems().add(playerNameItemBase);
        onlineListView.getItems().add(playerNameItemBase1);
        onlineListView.getItems().add(playerNameItemBase2);
        onlineListView.getItems().add(playerNameItemBase3);
        onlineListView.getItems().add(playerNameItemBase4);
        onlineListView.getItems().add(playerNameItemBase5);

        offlineListView.setLayoutX(409.0);
        offlineListView.setLayoutY(114.0);
        offlineListView.setPrefHeight(253.0);
        offlineListView.setPrefWidth(240.0);
        offlineListView.getStyleClass().add("mylistview");
                
        PlayerNameItemBase playerNameItemBase6 = new PlayerNameItemBase();
        playerNameItemBase6.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase7 = new PlayerNameItemBase();
        playerNameItemBase7.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase8 = new PlayerNameItemBase();
        playerNameItemBase8.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase9 = new PlayerNameItemBase();
        playerNameItemBase9.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase10 = new PlayerNameItemBase();
        playerNameItemBase10.playerNameId.setText("Fatma Hassan");
        
        PlayerNameItemBase playerNameItemBase11 = new PlayerNameItemBase();
        playerNameItemBase11.playerNameId.setText("Fatma Hassan");
        
        offlineListView.getItems().add(playerNameItemBase6);
        offlineListView.getItems().add(playerNameItemBase7);
        offlineListView.getItems().add(playerNameItemBase8);
        offlineListView.getItems().add(playerNameItemBase9);
        offlineListView.getItems().add(playerNameItemBase10);
        offlineListView.getItems().add(playerNameItemBase11);

        backButtonId.setFitHeight(70.0);
        backButtonId.setFitWidth(70.0);
        backButtonId.setLayoutX(14.0);
        backButtonId.setLayoutY(396.0);
        backButtonId.setPickOnBounds(true);
        backButtonId.setPreserveRatio(true);
        backButtonId.setImage(new Image(getClass().getResource("Photos/back.png").toExternalForm()));
        
        backButtonId.setOnMousePressed((event)->{
                
           

                        Parent root = new MainScreenBase();
                        Scene scene = new Scene(root, 750, 480);
                       
                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        
                        
                    
                });
         
         

        getChildren().add(imageView);
        getChildren().add(rectangle);
        getChildren().add(rectangle0);
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(rectangle1);
        getChildren().add(rectangle2);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(onlineListView);
        getChildren().add(offlineListView);
        getChildren().add(backButtonId);

    }
}
