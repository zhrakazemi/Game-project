package com.example.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageController {

    @FXML
    private Button btnExit;

    @FXML
    private Button btnResume;

    @FXML
    private Button btnStart;

    @FXML
    void pressOnBtnExit(ActionEvent event) {

    }

    @FXML
    void pressOnBtnResume(ActionEvent event) {
        Stage stage = (Stage) btnStart.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = null;
        try {
            root = (AnchorPane) FXMLLoader.load(HelloApplication.class.getResource("ViewScores.fxml"));
        } catch (IOException s) {
            throw new RuntimeException(s);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void pressOnBtnStart(ActionEvent event) {
        Stage stage = (Stage) btnStart.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        AnchorPane root = null;
        try {
            root = (AnchorPane) FXMLLoader.load(HelloApplication.class.getResource("GetPlayerName.fxml"));
        } catch (IOException s) {
            throw new RuntimeException(s);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    }
