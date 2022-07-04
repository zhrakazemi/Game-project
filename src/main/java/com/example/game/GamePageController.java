package com.example.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GamePageController extends Pane {
    ArrayList<Hero> allHeroes = new ArrayList<>();
    Timeline timeline;
    public static int money = 25;
    Group heros;
    Button btnSave = new Button("Save");
    Button btnExit = new Button("Exit");
    GamePageController() {
        this.minHeight(1080);
        this.minWidth(475);
        Image backGroundImage = new Image(HelloApplication.class.getResourceAsStream("Myproject.jpg"));
        ImageView backGroundImageView = new ImageView(backGroundImage);
        dragHeros();
        this.getChildren().add(backGroundImageView);
        this.getChildren().add(heros);
        creatTA();
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> update()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        btnSave.setOnAction(e -> {
            //Write money in data base
        });
        btnExit.setOnAction(e -> {
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            TabPane root = null;
            try {
                root = (TabPane) FXMLLoader.load(HelloApplication.class.getResource("FirstPage.fxml"));
            } catch (IOException s) {
                throw new RuntimeException(s);
            }
            Scene scene = new Scene(root, 400, 700);
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        heros.getChildren().get(3).setVisible(false);
    }

    void update() {
        ArrayList<Hero> heroArrayList = new ArrayList<>(allHeroes);
        for (Hero a : allHeroes) {
            for (Hero b : allHeroes) {
                if (a != b && a.getBoundsInParent().intersects(b.getBoundsInParent())) {
                    if ((a instanceof NormalStudent ||
                        a instanceof SmartStudent ||
                        a instanceof FighterStudent||
                            a instanceof Master) &&(
                                b instanceof HeadTA ||
                        b instanceof LazyTA ||
                        b instanceof ArmoredTA)){
                    heroArrayList.remove(a);
                    heroArrayList.remove(b);
                    new Thread(()->{
                        while (true){
                            if (a.health<=0){
                                if (a instanceof LazyTA ||
                                a instanceof  HeadTA ||
                                a instanceof ArmoredTA){
                                    GamePageController.money+=25;
                                    String sql = String.format("update score set score='%s' where name = '%s'", money,GetPlayerNameController.name);
                                    Connection con;
                                    try {
                                        con = DriverManager.getConnection("jdbc:mysql://localhost/game", "root", "");
                                        Statement s = con.prepareStatement(sql);
                                        s.execute(sql);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                allHeroes.remove(a);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        HelloApplication.gamePageController.getChildren().remove(a);
                                    }
                                });
                                break;
                            }
                            if (b.health<=0){
                                if (b instanceof LazyTA ||
                                        b instanceof  HeadTA ||
                                        b instanceof ArmoredTA){
                                    GamePageController.money+=25;
                                    String sql = String.format("update score set score='%s' where name = '%s'", money,GetPlayerNameController.name);
                                    Connection con;
                                    try {
                                        con = DriverManager.getConnection("jdbc:mysql://localhost/game", "root", "");
                                        Statement s = con.prepareStatement(sql);
                                        s.execute(sql);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                allHeroes.remove(b);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        HelloApplication.gamePageController.getChildren().remove(b);
                                    }
                                });
                                break;
                            }
                            a.health=(a.health-b.power);
                            b.health=(b.health-a.power);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).start();
            }
        }
            }
    }
        for (Hero hero:heroArrayList) {
            hero.setTranslateX(hero.getTranslateX() + hero.speed);
            if (hero.getTranslateX() < 120 && (hero instanceof HeadTA || hero instanceof LazyTA || hero instanceof ArmoredTA)) {
                timeline.stop();
                Alert alert6 = new Alert(Alert.AlertType.INFORMATION);
                alert6.setTitle("Hey You");
                alert6.setContentText("Game over");
                alert6.show();
            }
        }
        if(money>=75){
            heros.getChildren().get(3).setVisible(true);
        }

}

    public void dragHeros() {
        heros = new Group(
                new NormalStudent(100, 0),
                new SmartStudent(300, 0),
                new FighterStudent(500, 0),
                new Master(700 , 0)
        );
        for (Node a : heros.getChildren()) {
            a.setOnMousePressed(e -> {
                if (a instanceof SmartStudent) {
                    allHeroes.add(((SmartStudent) a).copy());
                }
                if (a instanceof NormalStudent) {
                    allHeroes.add(((NormalStudent) a).copy());
                }
                if (a instanceof FighterStudent) {
                    allHeroes.add(((FighterStudent) a).copy());
                }
                if (a instanceof Master) {
                    allHeroes.add(((Master) a).copy());
                }
                this.getChildren().add(allHeroes.get(allHeroes.size() - 1));
            });
            a.setOnMouseDragged(e -> {
                allHeroes.get(allHeroes.size() - 1).setTranslateX(e.getSceneX());
                allHeroes.get(allHeroes.size() - 1).setTranslateY(e.getSceneY());
            });
            a.setOnMouseReleased(e -> {
                if (allHeroes.get(allHeroes.size() - 1).getTranslateY() < 80 || allHeroes.get(allHeroes.size() - 1).getTranslateY() > 450
                        || allHeroes.get(allHeroes.size() - 1).getTranslateX() < 170 || allHeroes.get(allHeroes.size() - 1).getTranslateX() > 1000) {
                    allHeroes.get(allHeroes.size() - 1).setVisible(false);
                    this.getChildren().remove(allHeroes.get(allHeroes.size() - 1));
                    allHeroes.remove(allHeroes.get(allHeroes.size() - 1));
                } else {
                    if (allHeroes.get(allHeroes.size() - 1).getTranslateY() > 80 && allHeroes.get(allHeroes.size() - 1).getTranslateY() < 200) {
                        allHeroes.get(allHeroes.size() - 1).setTranslateY(100);
                    } else if (allHeroes.get(allHeroes.size() - 1).getTranslateY() > 200 && allHeroes.get(allHeroes.size() - 1).getTranslateY() < 330) {
                        allHeroes.get(allHeroes.size() - 1).setTranslateY(220);
                    } else {
                        allHeroes.get(allHeroes.size() - 1).setTranslateY(340);
                    }
                }
            });
        }

    }

    void creatTA() {
        new Thread(() -> {
            Hero temp = new LazyTA(950, 100);
            allHeroes.add(temp);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Hero temp1 = new LazyTA(800, 220);
            allHeroes.add(temp1);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Hero temp2 = new HeadTA(870, 340);
            allHeroes.add(temp2);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Hero temp3 = new ArmoredTA(850, 220);
            allHeroes.add(temp3);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Hero temp4 = new LazyTA(800, 100);
            allHeroes.add(temp4);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Hero temp5 = new LazyTA(850, 340);
            Hero temp6 = new HeadTA(800, 100);
            Hero temp7 = new ArmoredTA(950, 100);
            Hero temp8 = new LazyTA(900, 220);
            Hero temp9 = new ArmoredTA(920, 340);
            allHeroes.add(temp5);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            allHeroes.add(temp6);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            allHeroes.add(temp7);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            allHeroes.add(temp8);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            allHeroes.add(temp9);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    HelloApplication.gamePageController.getChildren().add(allHeroes.get(allHeroes.size() - 1));
                }
            });
            /*try {
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        }).start();
    }

}
