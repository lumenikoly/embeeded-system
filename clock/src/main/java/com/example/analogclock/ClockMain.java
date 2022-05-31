package com.example.analogclock;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClockMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(500, 500);
        ClockGraphics clockGraphics = new ClockGraphics(canvas.getGraphicsContext2D());

        AnchorPane anchorPane = new AnchorPane(canvas);
        anchorPane.setStyle("-fx-background-color: transparent;");

        MenuItem closeItem = new MenuItem("Закрыть");
        ContextMenu contextMenu = new ContextMenu(closeItem);

        closeItem.setOnAction(e -> primaryStage.close());
        anchorPane.setOnContextMenuRequested(e -> contextMenu.show(primaryStage, e.getScreenX(), e.getScreenY()));

        Scene scene = new Scene(anchorPane);
        scene.setFill(Color.TRANSPARENT);

        WindowDragger.addToParent(primaryStage, anchorPane);
        primaryStage.setTitle("Аналоговые часы");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setOnShown(e -> clockGraphics.start());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


