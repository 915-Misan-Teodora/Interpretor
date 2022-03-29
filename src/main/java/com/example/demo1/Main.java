package com.example.demo1;

import Exceptions.MyException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
        Parent mainWindow = fxmlLoader.load();

        MainWindowController mainWindowController = fxmlLoader.getController();

        stage.setTitle("Main Window");
        stage.setScene(new Scene(mainWindow, 1110, 800));
        stage.show();

        FXMLLoader secondLoader = new FXMLLoader();
        secondLoader.setLocation(getClass().getResource("SelectWindow.fxml"));
        Parent secondWindow = secondLoader.load();




        SelectWindowController sel = secondLoader.getController();
        sel.setMainWindowContr(mainWindowController);
        Stage secondStage = new Stage();
        secondStage.setTitle("Select Window!");
        secondStage.setScene(new Scene(secondWindow, 600, 650));
        secondStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}