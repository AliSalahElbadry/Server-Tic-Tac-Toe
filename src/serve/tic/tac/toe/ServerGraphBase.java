package serve.tic.tac.toe;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ServerGraphBase extends AnchorPane {

    protected final ImageView imageView;
    protected final Rectangle rectangle;
    protected final CategoryAxis categoryAxis;
    protected final NumberAxis numberAxis;
    protected static BarChart barChart;
    protected final ImageView backButtonId;
    protected final ImageView imageView0;
   

    public ServerGraphBase() {

        imageView = new ImageView();
        rectangle = new Rectangle();
        categoryAxis = new CategoryAxis();
        numberAxis = new NumberAxis();
        barChart = new BarChart(categoryAxis, numberAxis);
        backButtonId = new ImageView();
        imageView0 = new ImageView();
        
        setId("AnchorPane");
        setPrefHeight(480.0);
        setPrefWidth(750.0);
        getStylesheets().add("/serve/tic/tac/toe/BarchartStyle.css");

        imageView.setFitHeight(480.0);
        imageView.setFitWidth(750.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("Photos/background.jpg").toExternalForm()));

        rectangle.setArcHeight(100.0);
        rectangle.setArcWidth(100.0);
        rectangle.setFill(javafx.scene.paint.Color.valueOf("#2a47ad"));
        rectangle.setHeight(401.0);
        rectangle.setLayoutX(122.0);
        rectangle.setLayoutY(60.0);
        rectangle.setStroke(javafx.scene.paint.Color.valueOf("#2a47ad"));
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.getStyleClass().add("corner");
        rectangle.setWidth(526.0);

        categoryAxis.setSide(javafx.geometry.Side.BOTTOM);

        numberAxis.setSide(javafx.geometry.Side.LEFT);
        barChart.setLayoutX(178.0);
        barChart.setLayoutY(106.0);
        barChart.setPrefHeight(310.0);
        barChart.setPrefWidth(412.0);
        barChart.setStyle("-fx-background-color: #ffffff;");
        barChart.setTitle("Statistics of Players");

        backButtonId.setFitHeight(70.0);
        backButtonId.setFitWidth(70.0);
        backButtonId.setLayoutX(12.0);
        backButtonId.setLayoutY(406.0);
        backButtonId.setPickOnBounds(true);
        backButtonId.setPreserveRatio(true);
        backButtonId.setImage(new Image(getClass().getResource("Photos/back.png").toExternalForm()));
        backButtonId.setOnMousePressed((event) -> {

             ServeTicTacToe.scene.setRoot(new PlayersScreenBase());

        });
        System.out.println(PlayersScreenBase.offlineCount+"    "+PlayersScreenBase.onlineCount);
        imageView0.setFitHeight(113.0);
        imageView0.setFitWidth(357.0);
        imageView0.setLayoutX(220.0);
        imageView0.setLayoutY(3.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("Photos/logo.png").toExternalForm()));

        
        prepareChart();  
        getChildren().add(imageView);
        getChildren().add(rectangle);
        getChildren().add(barChart);
        getChildren().add(backButtonId);
        getChildren().add(imageView0);

    }
    public static void prepareChart()
    {
        barChart.setAnimated(false);
        barChart.getData().clear();
        System.out.println("prepareChart Called");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Online");
        series1.getData().add(new XYChart.Data<>("players", PlayersScreenBase.onlineCount));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("offline");
        series2.getData().add(new XYChart.Data<>("players", PlayersScreenBase.offlineCount));
        barChart.getData().addAll(series1, series2);
    }
}
