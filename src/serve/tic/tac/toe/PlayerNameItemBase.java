package serve.tic.tac.toe;


import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PlayerNameItemBase extends AnchorPane {

    protected final Rectangle rectangle;
    protected final Text playerNameId;

    public PlayerNameItemBase() {

        rectangle = new Rectangle();
        playerNameId = new Text();

        setId("AnchorPane");
        setPrefHeight(50.0);
        setPrefWidth(240.0);
        getStyleClass().add("mainFxmlClass");
        getStylesheets().add("/serve/tic/tac/toe/playernameitem.css");

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.valueOf("#305bc3"));
        rectangle.setHeight(53.0);
        rectangle.setStroke(javafx.scene.paint.Color.valueOf("#305bc3"));
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.getStyleClass().add("TextCorner");
        rectangle.setWidth(239.0);

        playerNameId.setFill(javafx.scene.paint.Color.WHITE);
        playerNameId.setLayoutX(28.0);
        playerNameId.setLayoutY(31.0);
        playerNameId.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        playerNameId.setStrokeWidth(0.0);
        playerNameId.setFont(new Font("Serif Regular", 20.0));

        getChildren().add(rectangle);
        getChildren().add(playerNameId);

    }
}
