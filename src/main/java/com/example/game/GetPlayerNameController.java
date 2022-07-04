package com.example.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.game.HelloApplication.gamePageController;

public class GetPlayerNameController {
    static String name;
    @FXML
    private TextField txtName;

    @FXML
    private Button btnDone;

    @FXML
    void pressOnBtnDone(ActionEvent event) {
        String sql = String.format("INSERT INTO score VALUES ('%s','0')", txtName.getText());
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/game", "root", "");
            Statement s = con.prepareStatement(sql);
            s.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        name=txtName.getText();
        Stage stage = (Stage) btnDone.getScene().getWindow();
        stage.close();
        gamePageController = new GamePageController();
        Scene scene = new Scene(gamePageController);
        // stage.setTitle("TA`s vs Student`s");
        stage.setScene(scene);
        stage.show();
    }
}
