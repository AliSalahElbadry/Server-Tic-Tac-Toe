package serve.tic.tac.toe;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SplashScreenBase extends AnchorPane {

    protected final ImageView imageView;
    protected final ImageView logoId;

    public SplashScreenBase() {

        imageView = new ImageView();
        logoId = new ImageView();

        setId("AnchorPane");
        setPrefHeight(480.0);
        setPrefWidth(750.0);

        imageView.setFitHeight(480.0);
        imageView.setFitWidth(750.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("Photos/background.jpg").toExternalForm()));

        logoId.setFitHeight(203.0);
        logoId.setFitWidth(583.0);
        logoId.setLayoutX(83.5);
        logoId.setLayoutY(138.5);
        logoId.setPickOnBounds(true);
        logoId.setPreserveRatio(true);
        logoId.setImage(new Image(getClass().getResource("Photos/logo.png").toExternalForm()));

        getChildren().add(imageView);
        getChildren().add(logoId);

       
    }
}
