package com.example.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.*;

public class ViewScoresController {

    @FXML
    private ListView<String> lvScores;
    public void initialize(){
        String sql = "select * FROM score";
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/game", "root", "");
            Statement s = con.prepareStatement(sql);
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                observableList.add(r.getString("name")+r.getString("score"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lvScores.setItems(observableList);
    }
}
